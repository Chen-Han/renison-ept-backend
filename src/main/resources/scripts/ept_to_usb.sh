if [ ! -d "/media/han/UUI" ]; then
	echo "no flash drive"
	exit 1
fi
rm -rf /media/han/UUI/ept-dist
cp -r $HOME/ept-dist /media/han/UUI/
if [[ `diff -r $HOME/ept-dist /media/han/UUI/ept-dist` ]]; then echo "File not successfully copied"; exit 1; fi
umount /media/han/UUI
