var ghApiKey = "fb03d204-d685-4044-a463-d57207621892";
var ghURL = "https://graphhopper.com/api/1/route/";
var request = require('request');
var async = require('async');
var Moment = require('moment');
const MomentRange = require('moment-range');
const moment = MomentRange.extendMoment(Moment);


var mongoose = require('mongoose'),
    Entry = mongoose.model('Entry');

exports.match = function (req, res) {
    Entry.find({}, function (err, entry) {
        if (err)
            res.send(err);

        var data = new Object;
        data = JSON.parse(JSON.stringify({}));
        var j = 0, mID = 0;
        while (entry[j]._id.toString() != req.params.entryID) {
            console.log(j + ". Vergleich : " + entry[0]._id.toString() + " mit " + req.params.entryID)
            j++;
        }

        var rangeJ = moment.range(new Date(entry[j].body.period.start), new Date(entry[j].body.period.end));

        url1 = ghURL + "?point=" + entry[j].body.route.start.lat + "," + entry[j].body.route.start.lng + "&point=" + entry[j].body.route.destination.lat + "," + entry[j].body.route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
        request(url1, { json: true }, (err, res1, body1) => {
            if (err) console.log(err);
            var i;
            //for (i = 0; i < entry.length; i++) {
            function requestDistance(n, callback) {
                console.log("WENN J: " + j + " NICHT n: " + n + " ist");
                var rangeN = moment.range(new Date(entry[n].body.period.start), new Date(entry[n].body.period.end));
                console.log(rangeJ);
                console.log(rangeN);
                console.log(rangeJ.overlaps(rangeN))
                if (entry[n].user != entry[j].user && rangeJ.overlaps(rangeN) == true) {

                    url = ghURL + "?point=" + entry[n].body.route.start.lat + "," + entry[n].body.route.start.lng + "&point=" + entry[j].body.route.start.lat + "," + entry[j].body.route.start.lng + "&point=" + entry[j].body.route.destination.lat + "," + entry[j].body.route.destination.lng + "&point=" + entry[n].body.route.destination.lat + "," + entry[n].body.route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
                    request(url, { json: true }, (err, res2, body2) => {
                        if (err) console.log(err);
                        //var disDifference = body2.paths[0].distance - body1.paths[0].distance;
                        var disEntry = (body1.paths[0].distance) / 1000;
                        var disTotal = (body2.paths[0].distance) / 1000;
                        var disDifference = disTotal - disEntry;
                        console.log(disEntry);
                        console.log(disTotal);


                        var obsScore = 0;

                        //Obstacles
                        if (entry[j].body.needObstacles.haveTransporter == true && entry[n].body.haveObstacles.haveTransporter == true) {
                            obsScore += 50;
                        } else if (entry[j].body.haveObstacles.haveTransporter == true) { obsScore += 50; }
                        if (entry[j].body.needObstacles.driveTransporter == true && entry[n].body.haveObstacles.driveTransporter == true) {
                            obsScore += 10;
                        } else if (entry[j].body.haveObstacles.driveTransporter == true) { obsScore += 10; }
                        if (entry[j].body.needObstacles.canMontate == true && entry[n].body.haveObstacles.canMontate == true) {
                            obsScore += 10;
                        } else if (entry[j].body.haveObstacles.canMontate == true) { obsScore += 10; }
                        if (entry[j].body.needObstacles.canInstall == true && entry[n].body.haveObstacles.canInstall == true) {
                            obsScore += 10;
                        } else if (entry[j].body.haveObstacles.canInstall == true) { obsScore += 10; }
                        if (entry[j].body.needObstacles.canDischarge == true && entry[n].body.haveObstacles.canDischarge == true) {
                            obsScore += 10;
                        } else if (entry[j].body.haveObstacles.canDischarge == true) { obsScore += 10; }
                        if (entry[j].body.needObstacles.canTransport == true && entry[n].body.haveObstacles.canTransport == true) {
                            obsScore += 10;
                        } else if (entry[j].body.haveObstacles.canTransport == true) { obsScore += 10; }

                        var disScore = (disEntry / disTotal * disDifference * disDifference) / disEntry;
                        var disProzent = 1;
                        if (disScore < 20) { disProzent = disProzent - (disScore / 20); }
                        else { disProzent = 0; }
                        disProzent = disProzent * 100;
                        var score = (disProzent * 0.5) + (obsScore * 0.5);

                        data[mID] = {
                            id: entry[n]._id.toString(),
                            score: score,
                            distanceScore: disProzent,
                            obsstacleScore: obsScore,
                            disScore: disScore
                        }
                        mID++;
                        console.log("EINTRAG " + mID + " ADDED");
                        console.log(data)

                        callback(null);
                    });
                } else { console.log("j ist n " + j + "=" + n); callback(null) };
            }
            async.timesSeries(entry.length, requestDistance, function (err, results) {
                if (err) res.send(err);

                res.json(data).end();

            });

        });
    })
}
