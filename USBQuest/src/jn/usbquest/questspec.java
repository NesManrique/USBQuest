package jn.usbquest;

import java.util.ArrayList;

public class questspec {
	private long id;
	//counter ==> counts how many times this quest it's done
	private long counter;
	private String title;
	//steps ==> array of strings describing each step of the quest
	private ArrayList<String> steps;
	//objective ==> State of each step. 0 unavailable, 1 available, 2 done
	private ArrayList<Integer> objectives; 
	//status ==> State of the quest. 0 unavailable, 1 available, 2 in progress  
	private long status;
	
	public long getId(){
		return id;
	}
	
	public void setId(long Id){
		this.id = Id;
	}
	
	public long getCounter(){
		return counter;
	}
	
	public void setCounter(long count){
		this.id = counter;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String Title){
		this.title = Title;
	}
	
	public long getStatus(){
		return status;
	}
	
	public void setStatus(long st){
		this.status = st;
	}
	
	public String getStep(int num){
		return steps.get(num);
	}
	
	public ArrayList<String> getSteps(){
		return this.steps;
	}
	
	public void addStep(String step){
		this.steps.add(step);
	}
	
	public void setStep(String step, int num){
		this.steps.set(num, step);
	}
	
	public void setSteps(ArrayList<String> S){
		this.steps = (ArrayList<String>) S.clone();
	}
	
	public Integer getObj(int num){
		return objectives.get(num);
	}
	
	public ArrayList<Integer> getObjs(){
		return this.objectives;
	}
	
	public void addObj(Integer step){
		this.objectives.add(step);
	}
	
	public void setObj(Integer step, int num){
		this.objectives.set(num, step);
	}
	
	public void setObj(ArrayList<Integer> S){
		this.objectives = (ArrayList<Integer>) S.clone();
	}
	
	public String toString(){
		return getTitle();
	}
}