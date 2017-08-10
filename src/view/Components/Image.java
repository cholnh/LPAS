package view.Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image extends Component{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7414364146693041689L;
	BufferedImage image;
	public Image(String imgPath)
	{
		try{
			image = ImageIO.read(new File(imgPath));
		}
		catch(Exception e)
		{
			image = null;
		}
	}
	
	@Override
	public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null); //그림을 그리는 메소드
    }
	
	@Override
	public Dimension getPreferredSize() {
        if (image == null) {
             return new Dimension(100,100); //그림 파일이 없을 경우 프레임의 크기를 100,100으로 만든다
        } else {
           return new Dimension(image.getWidth(null), image.getHeight(null)); //그림의 크기에 따라 화면의 크기를 변경한다.
       }
    }
}
