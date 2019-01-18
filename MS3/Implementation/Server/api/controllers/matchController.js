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
            console.log(j + ". Vergleich : " + entry[j]._id.toString() + " mit " + req.params.entryID)
            j++;
        }

        var rangeJ = moment.range(new Date(entry[j].period.start), new Date(entry[j].period.end));

        url1 = ghURL + "?point=" + entry[j].route.start.lat + "," + entry[j].route.start.lng + "&point=" + entry[j].route.destination.lat + "," + entry[j].route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
        request(url1, { json: true }, (err, res1, body1) => {
            if (err) console.log(err);
            var i;
            //for (i = 0; i < entry.length; i++) {
            function requestDistance(n, callback) {
                console.log("WENN J: " + j + " NICHT n: " + n + " ist");
                var rangeN = moment.range(new Date(entry[n].period.start), new Date(entry[n].period.end));
                console.log(rangeJ);
                console.log(rangeN);
                console.log(rangeJ.overlaps(rangeN))
                if (entry[n].userID != entry[j].userID && rangeJ.overlaps(rangeN) == true) {

                    url = ghURL + "?point=" + entry[n].route.start.lat + "," + entry[n].route.start.lng + "&point=" + entry[j].route.start.lat + "," + entry[j].route.start.lng + "&point=" + entry[j].route.destination.lat + "," + entry[j].route.destination.lng + "&point=" + entry[n].route.destination.lat + "," + entry[n].route.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
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
                        if (entry[j].needObstacles.haveTransporter == true && entry[n].haveObstacles.haveTransporter == true) {
                            obsScore += 50;
                        } else if (entry[j].haveObstacles.haveTransporter == true) { obsScore += 50; }
                        if (entry[j].needObstacles.driveTransporter == true && entry[n].haveObstacles.driveTransporter == true) {
                            obsScore += 10;
                        } else if (entry[j].haveObstacles.driveTransporter == true) { obsScore += 10; }
                        if (entry[j].needObstacles.canMontate == true && entry[n].haveObstacles.canMontate == true) {
                            obsScore += 10;
                        } else if (entry[j].haveObstacles.canMontate == true) { obsScore += 10; }
                        if (entry[j].needObstacles.canInstall == true && entry[n].haveObstacles.canInstall == true) {
                            obsScore += 10;
                        } else if (entry[j].haveObstacles.canInstall == true) { obsScore += 10; }
                        if (entry[j].needObstacles.canDischarge == true && entry[n].haveObstacles.canDischarge == true) {
                            obsScore += 10;
                        } else if (entry[j].haveObstacles.canDischarge == true) { obsScore += 10; }
                        if (entry[j].needObstacles.canTransport == true && entry[n].haveObstacles.canTransport == true) {
                            obsScore += 10;
                        } else if (entry[j].haveObstacles.canTransport == true) { obsScore += 10; }

                        var disScore = (disEntry / disTotal * disDifference * disDifference) / disEntry;
                        var disProzent = 1;
                        if (disScore < 20) { disProzent = disProzent - (disScore / 20); }
                        else { disProzent = 0; }
                        disProzent = disProzent * 100;
                        var score = (disProzent * 0.5) + (obsScore * 0.5);

                        //data[mID] = entry[n];
                        //data[mID]["score"] = score;
                        //data[mID]["distanceScore"] = disProzent;
                        //data[mID]["obsstacleScore"] = obsScore;
                        //data[mID]["disScore"] = disScore;
                        data[mID] = {
                            entry: entry[n],
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
