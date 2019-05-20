LogDir=/opt/tomcat/apache-tomcat-7.0.42/logs/
ResultDir=/root/stat/result
jsonDir=/root/stat/result
hname=`date  -d "3600 second ago" +%Y%m%d%H`
dname=`date  -d "3600 second ago" +%Y-%m-%d`
dname2=`date  -d "3600 second ago" +%Y%m%d`
mname=`date  -d "3600 second ago" +%Y-%m`
mname2=`date  -d "3600 second ago" +%Y%m`
prename=$hname
statname=$dname #hour and day is dname;monthly is mname
stattype=$1
if [ $stattype = hourly ]
then
	stname=hour
	prename=$hname
	statname=$dname
fi
if [ $stattype = daily ]
then
	stname=day
	prename=$dname2
	statname=$dname
fi
if [ $stattype = monthly ]
then
	stname=month
	prename=$mname2
	statname=$mname
fi
ProcFile=${LogDir}/localhost_access_log.${statname}
rm -rf $ResultDir/ll.txt
cat ${ProcFile}*txt|grep "\/stat\/"|while read line
do
	Dtimes_Y=`echo $line|cut -d "[" -f 2|cut -d "]" -f 1|cut -d "/" -f 3|cut -d ":" -f 1`
	Dtimes_M=`echo $line|cut -d "[" -f 2|cut -d "]" -f 1|cut -d "/" -f 2|sed "s/Jan/01/g"|sed "s/Feb/02/g"|sed "s/Mar/03/g"|sed "s/Apr/04/g"|sed "s/May/05/g"|sed "s/Jun/06/g"|sed "s/Jul/07/g"|sed "s/Aug/08/g"|sed "s/Sep/09/g"|sed "s/Oct/10/g"|sed "s/Nov/11/g"|sed "s/Dec/12/g"`
	Dtimes_D=`echo $line|cut -d "[" -f 2|cut -d "]" -f 1|cut -d "/" -f 1`
	Dtimes_H=`echo $line|cut -d "[" -f 2|cut -d "]" -f 1|cut -d "/" -f 3|cut -d ":" -f 2`
	wx_name=`echo $line|cut -d "\"" -f 2|cut -d "/" -f 2`
	wx_mod=`echo $line|cut -d "\"" -f 2|cut -d "/" -f 4`
	wx_hash=`echo $line|cut -d "\"" -f 2|cut -d "/" -f 6`
	user_id=`echo $line|cut -d "\"" -f 2|cut -d "/" -f 7`
	if [ -z $user_id ]
	then
		user_id=0000000000000
	fi
	if [ $user_id = 1.1 ]
	then
		user_id=0000000000000
	fi
	echo ${Dtimes_Y}${Dtimes_M}${Dtimes_D}${Dtimes_H} ${user_id} ${wx_mod} ${wx_hash} ${wx_name} >> $ResultDir/ll.txt
done

echo "[" >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
for  modname in `cat $ResultDir/ll.txt | grep ^"${prename}"|cut -d " " -f 3|sort|uniq`
do
	pvnum=`cat $ResultDir/ll.txt | grep "${prename}"|grep "$modname"|wc -l`
	echo {\"page\": \"${modname}\", >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
	echo \"pv\" : ${pvnum}, >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
	uvnum=`cat $ResultDir/ll.txt | grep "${prename}"|grep "$modname"|cut -d " " -f 2|sort|uniq|wc -l`
	echo \"uv\" : ${uvnum} >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
	echo }, >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
	echo $modname $pvnum $uvnum >> ${ResultDir}/$prename.txt
done	
echo "]" >> ${jsonDir}/stat-gtmc_wx-${stname}-${statname}.json
