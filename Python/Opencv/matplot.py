import matplotlib.pylab as plt
import cv2

imgBGR = cv2.imread('cat.bmp')
imgRGB = cv2.cvtColor(imgBGR,cv2.COLOR_BGR2RGB)

plt.axis('off')
plt.imshow(imgRGB)
plt.show()

imgGray = cv2.imread('cat.bmp',cv2.IMREAD_GRAYSCALE)

plt.axis('off')
plt.imshow(imgGray,cmap='gray')
plt.show()
