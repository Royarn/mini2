package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class Parcel6 {

    public String track() {
        return internalTracking(true);
    }

    private String internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;
                TrackingSlip(String id) {
                    this.id = id;
                }

                String getId() { return id; }
            }
            TrackingSlip trackingSlip = new TrackingSlip("TrackingSlip_id");
            return trackingSlip.id;
        }
        return null;
    }

    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        String id = parcel6.track();
        System.out.println(id);
    }
}
