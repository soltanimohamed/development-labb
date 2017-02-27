package src.domain;
public class Actor{
  private int actor_id;
  private String name;
  private String sex;

  public Actor(String name, String sex){
    this.name = name;
    this.sex = sex;
  }
  public int id(){return actor_id;}
  public String name(){return name;}
  public String sex(){return sex;}
  public void setName(String name){this.name = name;}
  public void setTitle(String sex){this.sex = sex;}
  public void setId(int actor_id){this.actor_id = actor_id;}
  public String toString(){
    return name + " " + sex;
  }
}
