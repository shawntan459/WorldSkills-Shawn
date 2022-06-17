import cv2

img = cv2.imread("Resources/lena.jpg", 0)

print(type(img))
print(img)
# print(img.shape)
# (h, w, d) = img.shape
# print("width={}, height={}, depth={}".format(w, h, d))

cv2.imshow("my image", img)

cv2.waitKey(3000)
