package demo.orsoncharts;

public class DemoDescription
{
  private String className;
  private String fileName;
  private String descriptionFileName;
  
  public DemoDescription(String paramString1, String paramString2, String paramString3)
  {
    this.className = paramString1;
    this.fileName = paramString2;
    this.descriptionFileName = paramString3;
  }
  
  public String getClassName()
  {
    return this.className;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public String getDescriptionFileName()
  {
    return this.descriptionFileName;
  }
  
  public String toString()
  {
    return this.fileName;
  }
}


/* Location:           C:\Users\vinchs\Desktop\temp\
 * Qualified Name:     demo.orsoncharts.DemoDescription
 * JD-Core Version:    0.7.0.1
 */