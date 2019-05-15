package com.planet.treeplantations.Activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlLayer;
import com.planet.treeplantations.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import dmax.dialog.SpotsDialog;

public class KmlDemoActivity extends BaseDemoActivity {

    Boolean isDoneParsing = false;
    private GoogleMap mMap;
    private String kmll;
    private String nResult, Degrees;
    private String dataString;

    protected int getLayoutId() {
        return R.layout.kml_demo;

    }

    public void startDemo() {
        try {
            kmll = getIntent().getStringExtra("kmll");

            mMap = getMap();

            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // retrieveFileFromResource();
            retrieveFileFromUrl();
        } catch (Exception e) {
            Log.e("Exception caught", e.toString());
        }
    }

    private void retrieveFileFromResource() {
        try {
            KmlLayer kmlLayer = new KmlLayer(mMap, R.raw.campus, getApplicationContext());
            kmlLayer.addLayerToMap();
            moveCameraToKml(kmlLayer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void retrieveFileFromUrl() {
        String url = "http://web821.128.99.new.ocpwebserver.com" + kmll;
        new DownloadKmlFile("http://web821.128.99.new.ocpwebserver.com" + kmll).execute();
    }

    private void moveCameraToKml(KmlLayer kmlLayer) {




     /*
        for (KmlContainer container : kmlLayer.getContainers()) {
            Log.e("--2--", kmlLayer.getContainers().toString());
//            LatLngBounds.Builder builder1 = new LatLngBounds.Builder();
//            getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(builder1.build(), 1));
            for (KmlContainer nestedContainer : container.getContainers()) {
//                if (container.hasContainers()) {
//                    nestedContainer.getContainers();
//                }
                isDoneParsing = true;
                for (KmlPlacemark placemark : nestedContainer.getPlacemarks()) {
                    Log.i("MTAG", placemark.getStyleId());
                    //Iterable containers = kmlLayer.getContainers();
                    // KmlLineString lineString = (KmlLineString) placemark.getGeometry();
                    //  KmlMultiGeometry fddf=(KmlMultiGeometry)placemark.getGeometry();
                    KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
                    //Create LatLngBounds of the outer coordinates of the polygon
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                        builder.include(latLng);
                    }

//                    for (LatLng latLng : fddf.getGeometryObject()) {
//                        builder.include(latLng);
//                    }
                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;
                    getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), width, height, 1));
                }
            }
        }
        */


/*        for (KmlContainer container : kmlLayer.getContainers()) {

            if (container.hasContainers()) {
                for (KmlContainer nestedContainer : container.getContainers()) {
                    isDoneParsing = true;
                    for (KmlPlacemark placemark : nestedContainer.getPlacemarks()) {
                        KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                            builder.include(latLng);
                        }
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;
                        getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), width, height, 1));
                    }
                }
            } else {
                for (KmlContainer container1 : kmlLayer.getContainers()) {
                    isDoneParsing = true;
                    for (KmlPlacemark placemark : container1.getPlacemarks()) {
                        KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                            builder.include(latLng);
                        }
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;
                        getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), width, height, 1));
                    }
                }
            }


        }*/


        if (!isDoneParsing) {

            try {
                if (dataString.contains("<coordinates>")) {
                    String s = dataString.substring(dataString.indexOf("<coordinates>") + 13, dataString.indexOf("</coordinates>"));
                    if (s.contains("\t")) {
                        s = s.replaceAll("\t", "");
                    }
                    if (s.contains("n")) {
                        s = s.replaceAll("n", "");
                    }
                    String[] cordinates = s.split(",");
                    if (s.length() > 1) {
                        String lng = cordinates[0];
                        String lat = cordinates[1];
                        if (lat.contains(" ")) {
                            String[] ar_lat = lat.split(" ");
                            lat = ar_lat[0];
                        }
                        double _lng = Double.parseDouble(lng);
                        double _lat = Double.parseDouble(lat);
                        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(_lat, _lng), 13));
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }


           /* try {
                String s = dataString.substring(dataString.indexOf("<description>"), dataString.indexOf("</description>"));
                s = s.substring(s.lastIndexOf("["), s.indexOf("]"));
                String[] ar = s.split("<br>");
                String lat = "", lng = "";


                if (ar[0].contains("[Latitude:") || ar[0].contains("[latitude:") || ar[0].contains("[LATITUDE:")) {
                    lat = ar[0].substring(ar[0].indexOf(":") + 1).trim();
                    lng = ar[1].substring(ar[1].indexOf(":") + 1).trim();
                } else {
                    lng = ar[0].substring(ar[0].indexOf(":") + 1).trim();
                    lat = ar[1].substring(ar[1].indexOf(":") + 1).trim();
                }

                double _lng = Double.parseDouble(lng);
                double _lat = Double.parseDouble(lat);
                getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(_lat, _lng), 13));
                // getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 22.48333889,80.26142222), 12));
            } catch (Exception e) {
                e.getCause();
                if (dataString.contains("<latitude>") || dataString.contains("<Latitude>") || dataString.contains("<LATITUDE>")) {
                    String lat = "";
                    if (dataString.contains("<latitude>")) {
                        lat = dataString.substring(dataString.indexOf("<latitude>") + 10, dataString.indexOf("</latitude>"));
                        lat = dataString.substring(dataString.indexOf("<latitude>") + 10, dataString.indexOf("</latitude>"));


                    }
                    if (dataString.contains("<Latitude>")) {
                        lat = dataString.substring(dataString.indexOf("<Latitude>") + 10, dataString.indexOf("</Latitude>"));
                    }
                    if (dataString.contains("<LATITUDE>")) {
                        lat = dataString.substring(dataString.indexOf("<LATITUDE>") + 10, dataString.indexOf("</LATITUDE>"));
                    }
                }

            }//*/
        }
    }

    public String getContents(String url) {
        String contents = "";
        try {
            URLConnection conn = new URL(url).openConnection();
            InputStream in = conn.getInputStream();
            contents = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.v("MALFORMEDPTION", e + "");
        } catch (IOException e) {
            Log.e(e.getMessage(), "" + e);
        }

        return contents;
    }

    private String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new
                InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private class DownloadKmlFile extends AsyncTask<String, Void, byte[]> {
        private String mUrl;
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(KmlDemoActivity.this).setCancelable(false).build();

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            super.onPreExecute();
            dialog.show();

        }
        public DownloadKmlFile(String url) {
            mUrl = url;
        }

        protected byte[] doInBackground(String... params) {
            try {
                if (mUrl.contains(" ")) {
                    mUrl = mUrl.replaceAll(" ", "%20");
                }
                InputStream is = new URL(mUrl).openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();

                dataString = getContents(mUrl);

                return buffer.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(byte[] byteArr) {
            try {
                KmlLayer kmlLayer = new KmlLayer(mMap, new ByteArrayInputStream(byteArr),
                        getApplicationContext());
                kmlLayer.addLayerToMap();


                kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                    @Override
                    public void onFeatureClick(Feature feature) {
                        /*Toast.makeText(KmlDemoActivity.this,
                                "Feature clicked:" + feature.getId(),
                                Toast.LENGTH_SHORT).show();*/
                    }
                });
                dialog.dismiss();
                moveCameraToKml(kmlLayer);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
