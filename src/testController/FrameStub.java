package testController;


/**
 * @type FrameStub.java
 * @author zhoujishi
 * @version 1.0
 * @description this is a stub class used for frame
 */
public class FrameStub {
    /** @type int
     *  @description this is a field only to track the changeView() invocation
     *  
     */
    private int view = 0;
	
	FrameStub(){}
	
	/**
	 * @method changeView
	 * @description This is a method to mock the changeView function of 
	 *  frame
	 */
	public void changeView(int step){
		view += step;
	}
	
	
	/**
	 * @method getView
	 * @return view
	 * @description this is a method for test whether the view has changed
	 */
	public int getView(){
		return view;
	}
}
