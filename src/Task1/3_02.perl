my $wasFirst = 0;
my $wasEmpty = 0;

while (my $line = <>) {
    $line =~ s/<.*?>//g;
    if ($wasFirst == 1) {
        if ($line =~ /^\s*$/) {
            $wasEmpty = 1;
            next;
        } else {
            if ($wasEmpty == 1) {
                print "\n";
                $wasEmpty = 0;
            }
            $line =~ s/^\s*(.*?)\s*$/$1/;
            $line =~ s/\s+/ /g;
            print $line;
            print "\n";
        }
    } else {
        if ($line =~ /\S/) {
            $line =~ s/^\s*(.*?)\s*$/$1/;
            $line =~ s/\s+/ /g;
            print $line;
            print "\n";
            $wasFirst = 1;
        }
    }
}

