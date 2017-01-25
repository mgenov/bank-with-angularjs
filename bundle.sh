rm -fr bin
mkdir bin
cp -fr frontend/build/assets bin/static
cp target/bank-1.0-SNAPSHOT.jar bin/bankApp.jar