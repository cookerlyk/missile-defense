import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class TutorialPane extends GraphicsPane{
	
	private static int numPages = 3;//change this to increase the number of pages
	
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GImage page[] = new GImage[numPages];
	private GButton backButton;
	
	private GButton nextPage;
	private GButton prevPage;
	
	
	
	private int currentPage;
	
	public TutorialPane(MainApplication app) {
		this.program = app;
		backButton = new GButton("Back", 100, 100, 100, 100);
		
		prevPage = new GButton("Prev Page", 100, 600, 100, 50);
		nextPage = new GButton("Next Page", 800, 600, 100, 50);
		
		//Pages must follow the naming convention. Remove placeholder to change from placeholder to actual screens
		for (int i = 0; i < numPages; i++){
			page[i] = new GImage("Screens/tutorial_"+ i +"_placeholder.png",0, 0);
		}
	}
	
	
	private void nextPage(){
		program.add(page[currentPage+1]);
		program.remove(page[currentPage]);
		currentPage +=1;
		
		if(currentPage == numPages-1){
			program.remove(nextPage);
		}
		program.add(prevPage);
		page[currentPage].sendToBack();
	}
	
	private void prevPage(){
		program.add(page[currentPage-1]);
		program.remove(page[currentPage]);
		currentPage -=1;
		
		if(currentPage == 0){
			program.remove(prevPage);
		}
		program.add(nextPage);
		page[currentPage].sendToBack();
	}
	
	@Override
	public void showContents() {
		program.add(page[0]);
		currentPage = 0;
		program.add(backButton);
		program.add(nextPage);
	}

	
	@Override
	public void hideContents() {
		program.remove(page[currentPage]);
		program.remove(backButton);
		program.remove(nextPage);
		program.remove(prevPage);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == backButton) {
			program.switchBack();
		}
		
		if(obj == nextPage && currentPage != numPages){
			this.nextPage();
		}
		
		if(obj == prevPage && currentPage != 0){
			this.prevPage();
		}
	}
	
}
