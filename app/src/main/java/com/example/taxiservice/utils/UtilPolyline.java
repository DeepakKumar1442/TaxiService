package com.example.taxiservice.utils;



import java.util.List;

public class UtilPolyline {

    public static String encodePolyline(List<LatLng> points) {
        StringBuilder encoded = new StringBuilder();
        int previousLat = 0;
        int previousLng = 0;

        for (LatLng point : points) {
            int lat = (int) (point.getLatitude() * 1E5);
            int lng = (int) (point.getLongitude() * 1E5);
            int dLat = lat - previousLat;
            int dLng = lng - previousLng;

            previousLat = lat;
            previousLng = lng;

            encode(dLat, encoded);
            encode(dLng, encoded);
        }

        return encoded.toString();
    }

    private static void encode(int value, StringBuilder encoded) {
        int shifted = value << 1;
        if (value < 0) {
            shifted = ~shifted;
        }
        while (shifted >= 0x20) {
            encoded.append((char) ((0x20 | (shifted & 0x1f)) + 63));
            shifted >>= 5;
        }
        encoded.append((char) (shifted + 63));
    }

    public static class LatLng {
        private final double latitude;
        private final double longitude;

        public LatLng(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}

