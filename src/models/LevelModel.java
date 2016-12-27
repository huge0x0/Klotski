package models;

public class LevelModel {
	
	
	public class PieceInformation{
		private int mX;
		private int mY;
		private int mWidth;
		private int mHeight;
		
		public PieceInformation(int X,int Y,int width,int height) {
			mX=X;
			mY=Y;
			mWidth=width;
			mHeight=height;
		}

		public int getX() {
			return mX;
		}

		public void setX(int X) {
			mX = X;
		}

		public int getY() {
			return mY;
		}

		public void setY(int Y) {
			mY = Y;
		}

		public int getWidth() {
			return mWidth;
		}

		public void setWidth(int width) {
			mWidth = width;
		}

		public int getHeight() {
			return mHeight;
		}

		public void setHeight(int height) {
			mHeight = height;
		}
	}
}
