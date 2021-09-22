package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {

  @Override
  public boolean matchJpeg(String filename) {
    return filename.matches("(.+)\\.(jpe?g|JPE?G)$");
  }

  @Override
  public boolean matchIp(String ip) {
    return ip.matches("(\\d{0,3}\\.){3}\\d{0,3}");
  }

  @Override
  public boolean isEmptyLine(String line) {
    return line.matches("(^$|\\s+)");
  }
}
