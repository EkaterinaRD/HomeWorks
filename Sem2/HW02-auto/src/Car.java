public class Car {

    protected String name, model;//, type;
    protected int size;//, power, release;

    Car(String name, String model, int size) {
        this.name = name;
        this.model = model;
        this.size = size;
    }

    String Information(){
        return "Inforamation about \" " + name +
                "\":\nModel: " + model +
                ";\nSize: " + size + ".";
    }
}
