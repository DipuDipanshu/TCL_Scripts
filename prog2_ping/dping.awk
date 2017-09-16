#!/usr/bin/awk-f

BEGIN{
        pingPkt=0;
    

}
{
        if(($1=="d")&&($5=="ping")){pingPkt ++;}
}
END{
        printf("No. of PING packets Dropped %d\n",pingPkt);
        
}
