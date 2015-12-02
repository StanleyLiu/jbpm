package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageDone {
	/**
	 * 图像缩放
	 * 
	 * @param srcPath
	 * @param targetPath
	 * @param scale
	 */
	public void zoom(String srcPath, String targetPath, float scale) {

		try {
			BufferedImage src = ImageIO.read(new File(srcPath)); // 读入源图像
			int width = src.getWidth(); // 源图宽
			int height = src.getHeight(); // 源图高

			// 获取一个宽、长是原来scale的图像实例
			Image image = src.getScaledInstance((int) (width * scale),
					(int) (height * scale), Image.SCALE_DEFAULT);

			// 缩放图像
			BufferedImage tag = new BufferedImage((int) (width * scale),
					(int) (height * scale), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = tag.createGraphics();

			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();

			OutputStream out = new FileOutputStream(targetPath);
			ImageIO.write(tag, "JPG", out);// 输出
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("图像缩放--ImageDone");
		new ImageDone().zoom("/Users/Stanley/照片/3.jpg", "/Users/Stanley/照片/33.jpg", 1f);
	}
}
