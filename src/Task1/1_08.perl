while (<>) {
print if /\([^\)]*\w+[^\(]*\)/;
}