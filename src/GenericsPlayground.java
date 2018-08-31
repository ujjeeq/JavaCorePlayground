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

        Value<?> getty(Key<? extends B> key) {
            return map.get(key);
        }

        //Generic method
        <T extends B> void modify(Map<Key<T>, Value<T>> map) {
        }

        //Generic method
        <B> void moooooodify(Map<Key<B>, Value<B>> map) {
        }

        //Generic method
        <T> void modifyyyyyy(Map<Key<T>, Value<T>> map) {
        }

        //member method
         void modiiiify(Map<Key<B>, Value<B>> map) {
        }

        //Generic method
        <T> void mmmmmmmodify(Map<Key<B>, Value<B>> map) {
        }
    }

    private MyTypeMap<Number> myTypeMap = new MyTypeMap<>();

    private void test() {
        Key<Number> numberKey = new Key<Number>() {};
        myTypeMap.put(numberKey, new Value<Long>() {});

        Value<? extends Number> value1 = myTypeMap.get(numberKey);
        Value<?> value2 = myTypeMap.get(numberKey);
        assert (value1 == value2);//add -ea in vm argument

        //cannot compile
        //Value<Object> value3 = myTypeMap.get(numberKey);
        //Value<Long> value3 = myTypeMap.get(numberKey);
        //Value<Number> value3 = myTypeMap.get(numberKey);

        Value<?> value4 = myTypeMap.getty(numberKey);

        //cannot compile
        //Value<? extends Number> value5 = myTypeMap.getty(numberKey);
        //Value<Long> value6 = myTypeMap.getty(numberKey);


        Map<Key<String>, Value<String>> keyValueMap = new HashMap<>();
        myTypeMap.modifyyyyyy(keyValueMap);
        myTypeMap.moooooodify(keyValueMap);
        //cannot compile
        //myTypeMap.modify(keyValueMap);


        Map<Key<Integer>, Value<Integer>> integerMap = new HashMap<>();
        myTypeMap.modify(integerMap);

        //cannot compile
        //myTypeMap.modiiiify(integerMap);
        //myTypeMap.mmmmmmmodify(integerMap);
    }
}
