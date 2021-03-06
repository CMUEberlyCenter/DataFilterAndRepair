clear
echo "Executing ..."

outputFile=$2

if [ -z "$1" ] ; then
    echo "No input file supplied"
    exit 1
fi

if [ -z "$2" ] ; then
    echo "No output file supplied, using input argument"
    outputFile="{$1}.anonymized.tsv"
fi

echo "Writing to: {$outputFile}"
echo $outputFile

exit 1

if type -p java; then
    echo found java executable in PATH
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo found java executable in JAVA_HOME     
    _java="$JAVA_HOME/bin/java"
else
    echo "no java"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo version "$version"
    if [[ "$version" > "1.5" ]]; then
        echo version is more than 1.5
    else         
        echo version is less than 1.5
    fi
fi

java -cp ./dist/datafiltering-1.0-SNAPSHOT-jar-with-dependencies.jar edu.cmu.eberly.DataFiltering --operation hashcode --target 2 --iformat t --oformat t --input $1 --output outputFile

