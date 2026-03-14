
public class ProxyPattern {
    public static void main(String[] args) {
        Image i=new ProxyImage("yash.png");
        i.display();
    }
}

interface Image
{
public void display();
}

class RealImage implements Image
{

    String name;

    RealImage(String name)
    {
        this.name=name;
    }

    @Override
    public void display() {
System.out.println("Image Name is RealImage"+name);
    }
}

class ProxyImage implements Image
{
    RealImage real;
    String name;
    ProxyImage(String name)
    {
        this.name=name;
        this.real=null;
    }
    @Override
    public void display() {
        if(real==null)
        {
            real=new RealImage(name);
        }
       System.out.println("Proxy Image "+ name);
    }
}
