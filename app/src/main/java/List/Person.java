package List;

/**
 * Created by chenming on 16/12/26.
 */

public class Person {
    private String mName;
    public Person(String name){
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person) obj;
        return p.mName.equals(mName);
    }
}
