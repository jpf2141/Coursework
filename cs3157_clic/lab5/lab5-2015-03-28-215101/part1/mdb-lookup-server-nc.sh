#!/bin/sh
on_ctrl_c() {
     echo "Ignoring Ctrl-C"
}
# Call on_ctrl_c() when the interrupt signal is received.
# The interrupt signal is sent when you press Ctrl-C.
trap on_ctrl_c INT



mkfifo mypipe-$$
cat mypipe-$$ | nc -l $1 | /home/jae/cs3157-pub/bin/mdb-lookup-cs3157 > mypipe-$$ 

rm -f mypipe-$$



