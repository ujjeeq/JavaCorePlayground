import java.util.HashMap;
import java.util.Map;

public class GenericsPlayground {

    public static void main(String[] args) {
        GenericsPlayground genericsPlayground = new GenericsPlayground();
        genericsPlayground.test();
    }


    interface Key<T> {
    }

    interface Value<T> {
    }

    class MyTypeMap<B> {
        private Map<Key<? extends B>, Value<? extends B>> map = new HashMap<>();

        void put(Key<? extends B> key, Value<? extends B> value) {
            map.put(key, value);
        }

        Value<? extends B> get(Key<? extends B> key) {
            return map.get(key);
        }

        <T extends B> void modify(Map<Key<T>, Value<T>> map) {
        }

        <T> void moooooodify(Map<Key<T>, Value<T>> map) {
        }
    }

    private MyTypeMap<Number> myTypeMap = new MyTypeMap<>();

    private void test() {
        Key<Number> numberKey = new Key<Number>() {};
        myTypeMap.put(numberKey, new Value<Long>() {});

        Value<? extends Number> value1 = myTypeMap.get(numberKey);
        Value<?> value2 = myTypeMap.get(numberKey);
        assert (value1 == value2);

        //cannot compile
        //Value<Object> value3 = myTypeMap.get(numberKey);


        Map<Key<String>, Value<String>> keyValueMap = new HashMap<>();
        //cannot compile
        //myTypeMap.modify(keyValueMap);

        myTypeMap.moooooodify(keyValueMap);
    }
}
