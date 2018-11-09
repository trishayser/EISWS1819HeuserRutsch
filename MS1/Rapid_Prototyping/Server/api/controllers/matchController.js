var ghApiKey = "a544f51a-099e-4c3d-b42c-5d8b5b777c10"
var ghURL = "https://graphhopper.com/api/1/route/";

var mongoose = require('mongoose'),
    Entry = mongoose.model('Entry');

exports.match = function (req, res) {
    Entry.find({}, function (err, entry) {
        if (err)
            res.send(err);

            
        //for (i=0; i<= entry.length; i++ ) {
        //    console.log(entry[i].body.lat);
        //};
        url1 = ghURL + "?point=" + entry[req.params.entryID].body.start.lat + "," + entry[req.params.entryID].body.start.lng + "?point=" + entry[req.params.entryID].body.destination.lat + "," + entry[req.params.entryID].body.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
        request(url1, { json: true }, (err, res1, body1) => {
            var i;
            for (i=0; i < entry.length; i++) {
                if (req.params.entryID != i) {
                    url = ghURL + "?point=" + entry[i].body.start.lat + "," + entry[i].body.start.lng + "?point=" + entry[req.params.entryID].body.start.lat + "," + entry[req.params.entryID].body.start.lng + "?point=" + entry[req.params.entryID].body.destination.lat + "," + entry[req.params.entryID].body.destination.lng + "?point=" + entry[i].body.destination.lat + "," + entry[i].body.destination.lng + "&vehicle=car&locale=de&key=" + ghApiKey;
                    request(url, { json: true }, (err, res2, body2) => {
                        distance = res2.paths[0].distance - res1.paths[0].distance;
                        console.log(distance);
                    });
                }
            }
            res.json(entry);
        });
    })
}
