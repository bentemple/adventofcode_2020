#!/bin/bash
for i in {1..9}; do
    mv day_0${i}/Problem_${i}_1.kt day_0${i}/Problem_0${i}_1.kt
    mv day_0${i}/Problem_${i}_2.kt day_0${i}/Problem_0${i}_2.kt
    sed -i "s/_$i/_0$i/g" day_0${i}/Problem_0${i}_1.kt
    sed -i "s/$i-1/0$i-1/g" day_0${i}/Problem_0${i}_1.kt
    sed -i "s/_$i/_0$i/g" day_0${i}/Problem_0${i}_2.kt
    sed -i "s/$i-2/0$i-2/g" day_0${i}/Problem_0${i}_2.kt
done
