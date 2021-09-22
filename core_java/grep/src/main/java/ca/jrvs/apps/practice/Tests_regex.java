package ca.jrvs.apps.practice;

public class Tests_regex {
  public static void main(String[] args){
    RegexExc match = new RegexExcImp();
    System.out.println("testing matchjpeg");
    System.out.println(match.matchJpeg(".jpg") + " expect false");
    System.out.println(match.matchJpeg(".jpeg") + " expect false");
    System.out.println(match.matchJpeg("ff.jpg") + " expect true");
    System.out.println(match.matchJpeg("ff.jpeg") + " expect true");
    System.out.println(match.matchJpeg(".jpg.fff") + " expect false");
    System.out.println(match.matchJpeg("a.jpg.") + " expect false");
    System.out.println(match.matchJpeg("a.jpg.g") + " expect false");
    System.out.println(match.matchJpeg(".jpeg.fff") + " expect false");
    System.out.println(match.matchJpeg("a.jpeg.") + " expect false");
    System.out.println(match.matchJpeg("a.jpeg.g") + " expect false");
    System.out.println(match.matchJpeg("a.jpeg g") + " expect false");
    System.out.println(match.matchJpeg("a.jpg g") + " expect false");
    System.out.println(match.matchJpeg("ff.JPG") + " expect true");
    System.out.println(match.matchJpeg("ff.JPEG") + " expect true");
    System.out.println("testing match_ip");
    System.out.println(match.matchIp("0.0.0.0") + " expect true");
    System.out.println(match.matchIp("9.9.9.9") + " expect true");
    System.out.println(match.matchIp("99.99.99.99") + " expect true");
    System.out.println(match.matchIp("999.999.999.999") + " expect true");
    System.out.println(match.matchIp("9.9.9.9.") + " expect false");
    System.out.println(match.matchIp("0.0.0.0000") + " expect false");
    System.out.println(match.matchIp("9999.9999.9999.9999") + " expect false");
    System.out.println(match.matchIp("99.99.99.99.") + " expect false");
    System.out.println(match.matchIp("999 999 999 999") + " expect false");
    System.out.println(match.matchIp(".9.9.9.9.") + " expect false");
    System.out.println(match.matchIp("O.9.9.9") + " expect false - passed big o, not zero");
    System.out.println("testing isEmptyLine");
    System.out.println(match.isEmptyLine("") + " expect true");
    System.out.println(match.isEmptyLine(" ") + " expect true");
    System.out.println(match.isEmptyLine("\n") + " expect true");
    System.out.println(match.isEmptyLine("\t") + " expect true");
    System.out.println(match.isEmptyLine("   ") + " expect true");
    System.out.println(match.isEmptyLine("        ") + " expect true");
    System.out.println(match.isEmptyLine("\n f") + " expect false");
    System.out.println(match.isEmptyLine("2\t ") + " expect false");

  }
}
