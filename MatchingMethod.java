public class MatchingMethod {
  private double a;
  private double b;
  private int j;
  private double e;

  MatchingMethod(double a,double b, double e) {
    this.a = a;
    this.b = b;
    this.e = e;
  }

  public double X(int k) {
    //System.out.println("X"+k+" = "+(a + k*(b-a)*0.1));
    return (a + k*(b-a)*0.1);
  }

  public boolean sameSign(double x, double y) {
    return ((x < 0 &&  y < 0) || (x > 0 && y > 0));
  }

  public double search(int lowerLimit, int upperLimit) { //System.out.println(j+"\t"+a+"\t"+b);
    boolean found = false;
    double x = 0.0;
    String direction;
    if(sameSign(f(X(5)),f(a))) direction = "right";
    else direction = "left";

    for(int i = lowerLimit + 1; i < upperLimit && !found; i++) {

      switch(direction) {
        case "right":
          if(!sameSign(f(X(5)), f(X(i)))) { //System.out.print(" R");
            //found interval for root
            found = true;
            x = (X(i-1) + X(i))/2; //System.out.print(" ** I = "+i+" ");
            if(sameSign(f(x),f(X(i-1)))) {
              this.a = x;
              this.b = X(i);
              //this.a = X(i-1);
              //this.b = x;
            }
            else {
              this.a = X(i-1);
              this.b = x;
              //this.a = x;
              //this.b = X(i);
            }

          }

        break;
        case "left":
          if(!sameSign(f(X(0)), f(X(i)))) { //System.out.print(" L");
            //found interval for root
            found = true;
            x = (X(i-1) + X(i))/2;  //System.out.print(" ** I = "+i+" ");
            if(sameSign(f(x),f(X(i)))) {
              //this.a = x;
              //this.b = X(i);
              this.a = X(i-1);
              this.b = x;
            }
            else {
              //this.a = X(i);
              //this.b = x;
              this.a = x;
              this.b = X(i);
            }

          }
        break;

      }

    }

    if(!found) {
      //set extreme interval
      if(direction == "right") { //System.out.print("Upper Limit");
        x = (X(9) + X(10))/2;
        if(sameSign(f(X(9)),f(x))) {
          this.a = x;
          this.b = X(10);
        }
        else {
          this.b = x;
          this.a = X(9);
        }
       }
      else { //System.out.print("Lower Limit");
        x = (X(1) + X(0))/2;
        if(sameSign(f(X(0)),f(x))) {
          this.a = x;
          this.b = X(1);
        }
        else {
          this.b = x;
          this.a = X(0);
        }
      }
    }

    return x;
  }

  public void run() {
    double x = 0.0;
    System.out.println("j a b");
    if((f(a)*f(b)) >= 0) {
      System.out.println("Invalid interval");
      return;
    }
    while(f(x) != 0 && (Math.abs(b - a) > e || j <= 20))
    {
      if(sameSign(f(X(5)), f(a))) { //search to the right
        //System.out.println("Going right");
        x = search(5, 10);
      }
      else { //search to the left
        //System.out.println("Going left");
        x = search(0,5);
      }
      j++;
      System.out.println(j+"\t"+a+"\t"+b+"\t"+x);
    }
    System.out.println("Root = "+x);
  }

  double f(double x) {
    //System.out.println("f"+x+" = "+(9*(x*x) - 4*x - 2));
  //  return (3*(x*x) + 2*x - 1);
  //return (4*(x*x)+3*x-1);
  //return (8*(x*x) + 3*x -5);
  //return (x*x + 3*x + 2);
  //return (3*(x*x) + x - 10);
  //return (Math.cos(x) -3*x + 1);
  return (x*Math.sin(x) + Math.cos(x));
  //return Math.pow(x,6) + Math.pow(x,4) - Math.pow(x,3) - 1;
  }
}
