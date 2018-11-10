var ghApiKey = "a544f51a-099e-4c3d-b42c-5d8b5b777c10"
var ghURL = "https://graphhopper.com/api/1/route/";
var request = require('request');
var async = require('async');


var mongoose = require('mongoose'),
    Entry = mongoose.model('Entry');

exports.match = function (req, res) {
    Entry.find({}, function (err, entry) {
        if (err)
            res.send(err);
        
        var data = new Object;
        data = JSON.parse(JSON.stringify({}));
        var j=0, mID= 0;
        while (entry[j]._id.toString() != req.params.entryID) {
            console.log(j + ". Vergleich : "+ entry[0]._id.toString() + " mit " + req.params.entryID)
            j++;
        }
        url1 = ghURL + "?point=" + entry[j].body.route.start.lat + "," + entry[j].body.route.start.lng + "&point=" + entry[j].body.route.destination.lat + "," + entry[j].body.route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
        request(url1, { json: true }, (err, res1, body1) => {
            var i;
            //for (i = 0; i < entry.length; i++) {
            function requestDistance(n, callback) {
                console.log("WENN J: "+ j + " NICHT n: " + n + " ist");
                
                if (j != n) {
                    
                    url = ghURL + "?point=" + entry[n].body.route.start.lat + "," + entry[n].body.route.start.lng + "&point=" + entry[j].body.route.start.lat + "," + entry[j].body.route.start.lng + "&point=" + entry[j].body.route.destination.lat + "," + entry[j].body.route.destination.lng + "&point=" + entry[n].body.route.destination.lat + "," + entry[n].body.route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
                    request(url, { json: true }, (err, res2, body2) => {
                        var distance = body2.paths[0].distance - body1.paths[0].distance;
                        var score = 0;
                        
                        //Obstacles 
                        if (entry[j].body.needObstacles.haveTransporter == true && entry[n].body.haveObstacles.haveTransporter == true) {
                            score += 50;
                        } else if (entry[j].body.haveObstacles.haveTransporter == true) {score +=50;}
                        if (entry[j].body.needObstacles.driveTransporter == true && entry[n].body.haveObstacles.driveTransporter == true) {
                            score += 10;
                        } else if (entry[j].body.haveObstacles.driveTransporter == true) {score +=10;}
                        if (entry[j].body.needObstacles.canMontate == true && entry[n].body.haveObstacles.canMontate == true) {
                            score += 10;
                        } else if (entry[j].body.haveObstacles.canMontate == true) {score +=10;}
                        if (entry[j].body.needObstacles.canInstall == true && entry[n].body.haveObstacles.canInstall == true) {
                            score += 10;
                        } else if (entry[j].body.haveObstacles.canInstall == true) {score +=10;}
                        if (entry[j].body.needObstacles.canDischarge == true && entry[n].body.haveObstacles.canDischarge == true) {
                            score += 10;
                        } else if (entry[j].body.haveObstacles.canDischarge == true) {score +=10;}
                        if (entry[j].body.needObstacles.canTransport == true && entry[n].body.haveObstacles.canTransport == true) {
                            score += 10;
                        } else if (entry[j].body.haveObstacles.canTransport == true) {score +=10;}
                        
                        data[mID]={
                            id : entry[n]._id.toString(),
                            distance: distance,
                            score: score
                        }
                        mID++;
                        console.log("EINTRAG " + mID + " ADDED");
                        console.log(data)
                        
                        callback(null);
                    });
                } else {console.log("j ist n " + j +"="+n ); callback(null)};
            }
            async.timesSeries(entry.length, requestDistance, function (err, results) {
                if (err) res.send(err);
                
                res.json(data).end();

            });

        });
    })
}
