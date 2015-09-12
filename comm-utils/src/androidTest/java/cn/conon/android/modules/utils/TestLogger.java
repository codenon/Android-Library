package cn.conon.android.modules.utils;

import junit.framework.Test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.test.AndroidTestCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.conon.android.modules.utils.log.Logger;

public class TestLogger extends AndroidTestCase {
    //    private String TAG = Logger.makeLogTag(getClass());
    private final String JSON = "{\"$schema\":\"http://json-schema.org/schema#\",\"title\":\"Product\",\"type\":\"object\",\"required\":[\"id\",\"name\",\"price\"],\"properties\":{\"id\":{\"type\":\"number\",\"description\":\"Product identifier\"},\"name\":{\"type\":\"string\",\"description\":\"Name of the product\"},\"price\":{\"type\":\"number\",\"minimum\":0},\"tags\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"stock\":{\"type\":\"object\",\"properties\":{\"warehouse\":{\"type\":\"number\"},\"retail\":{\"type\":\"number\"}}}}}";
    private final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><breakfast_menu><food><name>Belgian Waffles</name><price>$5.95</price><description>Our famous Belgian Waffles with plenty of real maple syrup</description><calories>650</calories></food><food><name>French Toast</name><price>$4.50</price><description>Thick slices made from our homemade sourdough bread</description><calories>600</calories></food><food><name>Homestyle Breakfast</name><price>$6.95</price><description>Two eggs, bacon or sausage, toast, and our ever-popular hash browns</description><calories>950</calories></food></breakfast_menu>";
    //----------------------------------------------------------------------------------------------
    boolean b = true;
    boolean[] bArray = new boolean[]{true, false, true, false};
    boolean[][] bDoubleArray = new boolean[][]{{true, false, true, false}, {true, false, true, false}, {true, false, true, false}};

    byte bt = 'a';
    byte[] btArray = new byte[]{'a', 'b', 'c', 'd'};

    char c = 'a';
    char[] cArray = new char[]{'a', 'b', 'c', 'd'};

    short s = 13;
    short[] sArray = new short[]{11, 12, 13, 14};

    int i = 13;
    int[] iArray = new int[]{11, 12, 13, 14};
    Integer[] intArray = new Integer[]{11, 12, 13, 14};

    long l = 13;
    long[] lArray = new long[]{11, 12, 13, 14};

    float f = 13;
    float[] fArray = new float[]{11, 12, 13, 14};

    double d = 13;
    double[] dArray = new double[]{11, 12, 13, 14};

    String str = "13";
    String[] strArray = new String[]{"11", "12", "13", "14"};

    CharSequence cs = "13";
    CharSequence[] csArray = new CharSequence[]{"11", "12", "13", "14"};

    SerializableClass serializable = new SerializableClass("name01", 11, 11);
    SerializableClass[] serializableArray = new SerializableClass[]{new SerializableClass("name01", 11, 11), new SerializableClass("name02", 12, 12), new SerializableClass("name03", 13, 13), new SerializableClass("name04", 14, 14)};

    ParcelableClass parcelable = new ParcelableClass("name01", 11, 11);
    ParcelableClass[] parcelableArray = new ParcelableClass[]{new ParcelableClass("name01", 11, 11), new ParcelableClass("name02", 12, 12), new ParcelableClass("name03", 13, 13), new ParcelableClass("name04", 14, 14)};

    ArrayList<CharSequence> csArrayList = new ArrayList<CharSequence>(Arrays.asList(csArray));
    ArrayList<Integer> intArrayList = new ArrayList<Integer>(Arrays.asList(intArray));
    ArrayList<String> stringArrayList = new ArrayList<String>(Arrays.asList(strArray));
    ArrayList<ParcelableClass> parcelableArrayList = new ArrayList<ParcelableClass>(Arrays.asList(parcelableArray));

    Map<String, String> strMap = new HashMap<String, String>() {{
        put("key1", "value1");
        put("key2", "value2");
        put("key3", "value3");
        put("key4", "value4");
    }};
    Map<String, SerializableClass> seriaMap = new HashMap<String, SerializableClass>() {{
        put("key1", new SerializableClass("name01", 11, 11));
        put("key2", new SerializableClass("name02", 12, 12));
        put("key3", new SerializableClass("name03", 13, 13));
        put("key4", new SerializableClass("name04", 14, 14));
    }};
    Map<String, ParcelableClass> parcelMap = new HashMap<String, ParcelableClass>() {{
        put("key1", new ParcelableClass("name01", 11, 11));
        put("key2", new ParcelableClass("name02", 12, 12));
        put("key3", new ParcelableClass("name03", 13, 13));
        put("key4", new ParcelableClass("name04", 14, 14));
    }};
    Map<Object, Object> objMap = new HashMap<Object, Object>() {{
        put("key1", new ParcelableClass("name01", 11, 11));
        put("key2", new SerializableClass("name02", 12, 12));
        put(new SerializableClass("name01", 11, 11), new ParcelableClass("name01", 11, 11));
        put(new ParcelableClass("name02", 12, 12), new SerializableClass("name02", 12, 12));
    }};

    //----------------------------------------------------------------------------------------------
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Logger.e("setUp()");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Logger.e("tearDown()");
    }

    public void testLogger() throws Exception {
        // adb shell setprop log.tag.cn.conon_TestLogger VEROBSE

        Logger.v("testLogger()");
        Logger.d("testLogger()");
        Logger.i("testLogger()");
        Logger.w("testLogger()");
        Logger.e("testLogger()");
        Logger.wtf("testLogger()");

        Logger.v("testLogger():%s", "args");
        Logger.d("testLogger():%s", "args");
        Logger.i("testLogger():%s", "args");
        Logger.w("testLogger():%s", "args");
        Logger.e("testLogger():%s", "args");
        Logger.wtf("testLogger():%s", "args");
        Logger.t("exc", 5).e(new Exception("exception"), "testLogger() exception:%s", "args");


        //------------------------------------------------------------------------------------------

        Intent intent = new Intent("com.example.intent.action");
        Bundle bundle = buildBundle();
        intent.putExtra("extra_bundle", buildBundle());
        intent.putExtras(bundle);

        Logger.t("tag", 100).d(intent, "testIntent:%s", "args");
        //------------------------------------------------------------------------------------------

        Logger.object(bArray);
        Logger.object(bDoubleArray);
        Logger.object(btArray);
        Logger.object(cArray);
        Logger.object(sArray);
        Logger.object(intArray);
        Logger.object(lArray);
        Logger.object(fArray);
        Logger.object(dArray);
        Logger.object(strArray);
        Logger.object(csArray);
        Logger.object(strArray);

        Logger.object(serializable);
        Logger.object(serializableArray);
        Logger.object(parcelable);
        Logger.object(parcelableArray);
        Logger.object(csArrayList);
        Logger.object(intArrayList);
        Logger.object(stringArrayList);
        Logger.object(parcelableArrayList);

        Logger.object(null);
        Logger.object(strMap);
        Logger.object(seriaMap);
        Logger.object(parcelMap);
        Logger.object(objMap);


        Logger.json(JSON);
        Logger.xml(XML);
    }

    public static Test suite() {
        TestLogger suite = new TestLogger();
        return suite;
    }

    private Bundle buildBundle() {


        Bundle bundle = new Bundle();

        bundle.putBoolean("extra_boolean", b);
        bundle.putBooleanArray("extra_boolean_array", bArray);
        bundle.putByte("extra_byte", bt);
        bundle.putByteArray("extra_byte_array", btArray);
        bundle.putChar("extra_char", c);
        bundle.putCharArray("extra_char_array", cArray);
        bundle.putShort("extra_short", s);
        bundle.putShortArray("extra_short_array", sArray);
        bundle.putInt("extra_int", i);
        bundle.putIntArray("extra_int_array", iArray);
        bundle.putLong("extra_long", l);
        bundle.putLongArray("extra_long_array", lArray);
        bundle.putFloat("extra_float", f);
        bundle.putFloatArray("extra_float_array", fArray);
        bundle.putDouble("extra_double", d);
        bundle.putDoubleArray("extra_double_array", dArray);
        bundle.putString("extra_string", str);
        bundle.putStringArray("extra_string_array", strArray);
        bundle.putCharSequence("extra_char_sequence", cs);
        bundle.putCharSequenceArray("extra_char_sequence_array", csArray);
        bundle.putSerializable("extra_serializable", serializable);
        bundle.putSerializable("extra_serializable_array", serializableArray);
        bundle.putParcelable("extra_parcelable", parcelable);
        bundle.putParcelableArray("extra_parcelable_array", parcelableArray);

        bundle.putCharSequenceArrayList("extra_char_sequence_array_list", csArrayList);
        bundle.putIntegerArrayList("extra_integer_array_list", intArrayList);
        bundle.putStringArrayList("extra_string_array_list", stringArrayList);
        bundle.putParcelableArrayList("extra_parcelable_array_list", parcelableArrayList);

        return bundle;
    }

    public static class SerializableClass implements Serializable {
        private String name;
        private int age;
        private float score;
        //        private SerializableClass serializableClass = new SerializableClass("name01", 11, 11);
        private ParcelableClass parcelableClass = new ParcelableClass("name01", 11, 11);


        boolean b = true;
        boolean[] bArray = new boolean[]{true, false, true, false};
        boolean[][] bDoubleArray = new boolean[][]{{true, false, true, false}, {true, false, true, false}, {true, false, true, false}};

        byte bt = 'a';
        byte[] btArray = new byte[]{'a', 'b', 'c', 'd'};

        char c = 'a';
        char[] cArray = new char[]{'a', 'b', 'c', 'd'};

        short s = 13;
        short[] sArray = new short[]{11, 12, 13, 14};

        int i = 13;
        int[] iArray = new int[]{11, 12, 13, 14};
        Integer[] intArray = new Integer[]{11, 12, 13, 14};

        long l = 13;
        long[] lArray = new long[]{11, 12, 13, 14};

        float f = 13;
        float[] fArray = new float[]{11, 12, 13, 14};

        double d = 13;
        double[] dArray = new double[]{11, 12, 13, 14};

        String str = "13";
        String[] strArray = new String[]{"11", "12", "13", "14"};

        CharSequence cs = "13";
        CharSequence[] csArray = new CharSequence[]{"11", "12", "13", "14"};


        ParcelableClass parcelable = new ParcelableClass("name01", 11, 11);
        ParcelableClass[] parcelableArray = new ParcelableClass[]{new ParcelableClass("name01", 11, 11), new ParcelableClass("name02", 12, 12), new ParcelableClass("name03", 13, 13), new ParcelableClass("name04", 14, 14)};

        ArrayList<CharSequence> csArrayList = new ArrayList<CharSequence>(Arrays.asList(csArray));
        ArrayList<Integer> intArrayList = new ArrayList<Integer>(Arrays.asList(intArray));
        ArrayList<String> stringArrayList = new ArrayList<String>(Arrays.asList(strArray));
        ArrayList<ParcelableClass> parcelableArrayList = new ArrayList<ParcelableClass>(Arrays.asList(parcelableArray));

        Map<String, String> strMap = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("key3", "value3");
            put("key4", "value4");
        }};

        Map<String, ParcelableClass> parcelMap = new HashMap<String, ParcelableClass>() {{
            put("key1", new ParcelableClass("name01", 11, 11));
            put("key2", new ParcelableClass("name02", 12, 12));
            put("key3", new ParcelableClass("name03", 13, 13));
            put("key4", new ParcelableClass("name04", 14, 14));
        }};


        public SerializableClass() {
        }

        public SerializableClass(String name, int age, float score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }
    }

    public static class ParcelableClass implements Parcelable {
        private String name;
        private int age;
        private float score;

        public ParcelableClass() {
        }

        public ParcelableClass(Parcel source) {
            readFromParcel(source);
        }

        public ParcelableClass(String name, int age, float score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        private void readFromParcel(Parcel in) {
            this.name = in.readString();
            this.age = in.readInt();
            this.score = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(age);
            dest.writeFloat(score);
        }

        public static final Parcelable.Creator<ParcelableClass> CREATOR = new Creator<ParcelableClass>() {
            @Override
            public ParcelableClass[] newArray(int size) {
                return new ParcelableClass[size];
            }

            @Override
            public ParcelableClass createFromParcel(Parcel source) {
                return new ParcelableClass(source);
            }
        };
    }
}
