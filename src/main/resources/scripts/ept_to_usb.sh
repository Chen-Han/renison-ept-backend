if [ ! -d "/media/han/UUI" ]; then
	echo "no flash drive"
	exit 1
fi
cp -r $HOME/ept-dist /media/han/UUI/ept-dist
if [[ `diff $HOME/ept-dist /media/han/UUI/ept-dist` ]]; then echo "File not successfully copied"; exit 1; fi
umount /media/han/UUI

