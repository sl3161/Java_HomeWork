package HW.HW6;

public class Notebooks {

String size_op_mem;
String size_hard_drive;
String price;
String name;
String os;
String color;


@Override
public String toString() {
    // TODO Auto-generated method stub
    return name + " " + os + " " + color +" " + size_op_mem + " " + size_hard_drive + " " + price + "\n";
}
    
}
