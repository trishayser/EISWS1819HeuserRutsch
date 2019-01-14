package de.thkoeln.heuserrutsch.client;

public class Entry {
    Number id;
    String userID;
    String date;
    String title;
    String startLat;
    String startLng;
    String destinationLat;
    String destinationLng;
    String start, end;
    Boolean hHaveTransporter, hDriveTransporter, hCanMontate, hCanInstall, hCanDischarge, hCanTransport;
    Boolean nHaveTransporter, nDriveTransporter, nCanMontate, nCanInstall, nCanDischarge, nCanTransport;
    String chargePack, chargeWeight, chargeHeight, chargeLength, chargeWidth;
    String matchedPartner, transporter;
    Boolean suceed, active;

    public Entry(String userID, String title) {
        this.userID = userID;
        this.title = title;
    }

    public Entry(String userID, String title, Boolean hHaveTransporter, Boolean hDriveTransporter, Boolean hCanMontate, Boolean hCanInstall, Boolean hCanDischarge, Boolean hCanTransport, Boolean nHaveTransporter, Boolean nDriveTransporter, Boolean nCanMontate, Boolean nCanInstall, Boolean nCanDischarge, Boolean nCanTransport) {
        this.userID = userID;
        this.title = title;
        this.hHaveTransporter = hHaveTransporter;
        this.hDriveTransporter = hDriveTransporter;
        this.hCanMontate = hCanMontate;
        this.hCanInstall = hCanInstall;
        this.hCanDischarge = hCanDischarge;
        this.hCanTransport = hCanTransport;
        this.nHaveTransporter = nHaveTransporter;
        this.nDriveTransporter = nDriveTransporter;
        this.nCanMontate = nCanMontate;
        this.nCanInstall = nCanInstall;
        this.nCanDischarge = nCanDischarge;
        this.nCanTransport = nCanTransport;
    }
    /*
    "id": Number,
            "userID": String,
            "date": String,
            "title": String,
            "route": {
        "start": {
            "lat": String,
                    "lng": String
        },
        "destination": {
            "lat": String,
                    "lng": String
        }
    },
            "period": {
        "start": String,
                "end": String
    },
            "needObstacles": {
        "haveTransporter": Boolean,
                "driveTransporter": Boolean,
                "canMontate": Boolean,
                "canInstall": Boolean,
                "canDischarge": Boolean,
                "canTransport": Boolean
    },
            "haveObstacles": {
        "haveTransporter": Boolean,
                "driveTransporter": Boolean,
                "canMontate": Boolean,
                "canInstall": Boolean,
                "canDischarge": Boolean,
                "canTransport": Boolean
    },
            "charge": {
        "package": String,
                "weight": String,
                "size": {
            "height": String,
                    "length": String,
                    "width": String
        },
    },
            "matchedPartner": String,
            "active": Boolean,
            "succeed": Boolean,
            "transporter": String
}



    //OLD
    /*Number id;
    String userID;
    String date;
    String title;
    Object route = new Object(){
        Object start = new Object(){
           String lat;
           String lng;
        };
        Object destination = new Object(){
            String lat;
            String lng;
        };
    };
    Object period = new Object(){
       String start, end;
    };
    Object haveObstacles = new Object() {
        Boolean haveTransporter, driveTransporter, canMontate, canInstall, canDischarge, canTransport;
    };
    Object needObstacles = new Object() {
        Boolean haveTransporter, driveTransporter, canMontate, canInstall, canDischarge, canTransport;
    };
    Object charge = new Object() {
       String pack, weight, height, length, width;
    };
    String matchedPartner, transporter;
    Boolean suceed, active;

    Entry(String userID, String title) {
        this.userID= userID;
        this.title = title;
        this.needObstacles.haveTransporter;
    }
    */
    /*
    "id": Number,
            "userID": String,
            "date": String,
            "title": String,
            "route": {
        "start": {
            "lat": String,
                    "lng": String
        },
        "destination": {
            "lat": String,
                    "lng": String
        }
    },
            "period": {
        "start": String,
                "end": String
    },
            "needObstacles": {
        "haveTransporter": Boolean,
                "driveTransporter": Boolean,
                "canMontate": Boolean,
                "canInstall": Boolean,
                "canDischarge": Boolean,
                "canTransport": Boolean
    },
            "haveObstacles": {
        "haveTransporter": Boolean,
                "driveTransporter": Boolean,
                "canMontate": Boolean,
                "canInstall": Boolean,
                "canDischarge": Boolean,
                "canTransport": Boolean
    },
            "charge": {
        "package": String,
                "weight": String,
                "size": {
            "height": String,
                    "length": String,
                    "width": String
        },
    },
            "matchedPartner": String,
            "active": Boolean,
            "succeed": Boolean,
            "transporter": String
}
*/
}
