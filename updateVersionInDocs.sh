#!/bin/sh

VERSION_PROPERTIES=version.properties

function getProperty {
   PROP_KEY=$1
   PROP_VALUE=`cat $VERSION_PROPERTIES | grep "$PROP_KEY" | cut -d'=' -f2`
   echo $PROP_VALUE
}

major=$(getProperty "major")
minor=$(getProperty "minor")
patch=$(getProperty "patch")

version="${major}.${minor}.${patch}"

echo "Retrieving version from $VERSION_PROPERTIES: [${version}]"

files="
  README.md
  tensorflow-litex/README.md
"

for f in ${files}; do
  if [ ! -f ${f} ]; then
    continue;
  fi

  echo "updating version in file [${f}]..."
  sed -i "" "s/tfiltex_version\ =\ \".*\"/tfiltex_version\ =\ \"${version}\"/g" ${f}
  sed -i "" "s/download.svg\?version=[0-9]\.[0-9]\.[0-9]/download.svg\?version=${version}/g" ${f}
  sed -i "" "s/maven\/tensorflow-litex\/[0-9]\.[0-9]\.[0-9]\//maven\/tensorflow-litex\/${version}\//g" ${f}
done

