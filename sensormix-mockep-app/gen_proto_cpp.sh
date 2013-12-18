mkdir -p target/build/
protoc --proto_path=src/main/resources --cpp_out=target/build/ src/main/resources/sample.proto
