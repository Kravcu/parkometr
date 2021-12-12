#MUST BE INVOKED AS postgres
#! /bin/bash

for i in $(seq 1 20)
do
  parking_place_id=$((100+${i}))

  random=$((RANDOM % 2))


  if [ "$random" -eq 1 ]
  then
    state="OCCUPIED"
    event="PARKING_PLACE_OCCUPIED"
  else
    state="FREE"
    event="PARKING_PLACE_RELEASED"
  fi
  psql -d wildfly -c "INSERT INTO parking_place_state_change VALUES(${parking_place_id},NOW(), '${state}', ${parking_place_id})"
  psql -d wildfly -c "INSERT INTO notification(id, generated, event, placeNumber, sectionNumber) VALUES($i, NOW(), '${event}', $(($i % 5 + 1)), 1)"
done
