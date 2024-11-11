<template>
  <div>
    <div>
      <button type="submit" @click="close">Закрыть вкладку</button>
    </div>
    <div>
      <div>
        <label>Name</label>
        <input type="text" v-model="name">
        <h7 v-if="namestatus && name===''" style="color: red">fsdfs</h7>
      </div>
      <div>
        <label>Coordinates</label>
        <div>
          <label>X</label>
          <input type="number" v-model="coordinates_x">
          <h7 v-if="coordinates_x<-137" style="color: red">dsf</h7>
        </div>
        <div>
          <label>Y</label>
          <input type="number" v-model="coordinates_y">
          <h7 v-if="coordinates_y>65" style="color: red">dsf</h7>
        </div>
      </div>
      <div>
        <label>Description</label>
        <input type="text" v-model="description">
        <h7 v-if="descriptionstatus && description===''" style="color: red">fsdfs</h7>
      </div>
      <div>
        <label>Difficulty</label>
        <select v-model="difficulty">
          <option value="-">-</option>
          <option>EASY</option>
          <option>NORMAL</option>
          <option>HARD</option>
          <option>VERY_HARD</option>
          <option>IMPOSSIBLE</option>
        </select>
        <h7 v-if="difficultystatus && difficulty==='-'" style="color: red">fsdfs</h7>
      </div>
      <div>
        <input type="checkbox" v-model="disciplinestatus">
      </div>
      <div v-if="disciplinestatus">
        <label>Discipline</label>
        <div>
          <label>Name</label>
          <input type="text" v-model="discipline_name">
          <h7 v-if="discipline_namestatus && discipline_name===''" style="color: red">fsdfs</h7>
        </div>
        <div>
          <label>Lecture Hours</label>
          <input type="number" v-model="discipline_lectureHours">
        </div>
      </div>
      <div>
        <label>Minimal Point</label>
        <input type="number" v-model="minimalPoint">
        <h7 v-if="minimalPoint<1" style="color: red">fsdfs</h7>
      </div>
      <div>
        <label>Average Point</label>
        <input type="number" v-model="averagePoint">
        <h7 v-if="averagePoint<1" style="color: red">fsdfs</h7>
      </div>
      <div>
        <input type="checkbox" v-model="personstatus">
      </div>
      <div v-if="personstatus">
        <label>Person</label>
        <div>
          <label>Name</label>
          <input type="text" v-model="person_name">
          <h7 v-if="person_namestatus && person_name===''" style="color: red">fsdfs</h7>
        </div>
        <div>
          <label>Eye color</label>
          <select v-model="person_eyeColor">
            <option value="-">-</option>
            <option>GREEN</option>
            <option>RED</option>
            <option>BROWN</option>
          </select>
          <h7 v-if="person_eyeColorstatus && person_eyeColor==='-'" style="color: red">fsdfs</h7>
        </div>
        <div>
          <label>Hair Color</label>
          <select v-model="person_hairColor">
            <option value="-">-</option>
            <option>GREEN</option>
            <option>RED</option>
            <option>BROWN</option>
          </select>
          <h7 v-if="person_hairColorstatus && person_hairColor==='-'" style="color: red">fsdfs</h7>
        </div>
        <div>
          <label>Location</label>
          <div>
            <label>X</label>
            <input type="number" v-model="person_location_x">
          </div>
          <div>
            <label>Y</label>
            <input type="number" v-model="person_location_y">
          </div>
          <div>
            <label>Z</label>
            <input type="number" v-model="person_location_z">
          </div>
        </div>
        <div>
          <label>Weight</label>
          <input type="number" v-model="person_weight">
          <h7 v-if="person_weight<1" style="color: red">fsdfs</h7>
        </div>
        <div>
          <label>Nationality</label>
          <select v-model="person_nationality">
            <option value="-">-</option>
            <option>CHINA</option>
            <option>VATICAN</option>
            <option>SOUTH_KOREA</option>
          </select>
          <h7 v-if="person_nationalitystatus && person_nationality==='-'" style="color: red">fsdfs</h7>
        </div>
      </div>
      <button type="submit" @click="update()">Обновить лабораторную работу</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dot:"",
      id: this.id,
      name: this.name,
      namestatus: false,
      coordinates_id:this.coordinates_id,
      coordinates_x: this.coordinates_x,
      coordinates_y: this.coordinates_y,
      description: this.description,
      descriptionstatus: false,
      difficulty: this.difficulty,
      difficultystatus: false,
      discipline_id:this.discipline_id,
      discipline_name: this.discipline_name,
      discipline_namestatus: false,
      discipline_lectureHours: this.discipline_lectureHours,
      minimalPoint: this.minimalPoint,
      averagePoint: this.averagePoint,
      person_id:this.person_id,
      person_name: this.person_name,
      person_namestatus: false,
      person_eyeColor: this.person_eyeColor,
      person_eyeColorstatus: false,
      person_hairColor: this.person_hairColor,
      person_hairColorstatus: false,
      person_location_id:this.person_location_id,
      person_location_x: this.person_location_x,
      person_location_y: this.person_location_y,
      person_location_z: this.person_location_z,
      person_weight: this.person_weight,
      person_nationality: this.person_nationality,
      person_nationalitystatus: false,
      creation_date:"",
      disciplinestatus:false,
      personstatus:false
    }
  },
  methods: {
    update() {
      //alert(localStorage.getItem('dot'))
      //this.dot=localStorage.getItem('dot')
     // alert(this.dot.name)
      if(this.validat()) {
        fetch("http://localhost:1298/api/LabWork/update", {
          method: "POST",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("jwt")
          }),
          body: JSON.stringify({
            id: this.id,
            name: this.name,
            coordinates:{id: this.coordinates_id,
              x: this.coordinates_x, y: this.coordinates_y},
            description: this.description,
            difficulty: this.difficulty,
            discipline:this.disciplineadd(),
            minimalPoint: this.minimalPoint,
            averagePoint: this.averagePoint,
            person: this.personadd(),
            permission: this.permission,
            creation_date:this.creation_date
          })
        }).then((response) => {
          if (response.ok) {
            window.close()
          } else {
            alert("Неверно введённые данные")
          }
        })
      }
    },
    validat() {
      alert(123131)
      var t = true
      if (this.name === "") {
        t = false
        this.namestatus = true
      }
      if (this.coordinates_x < -137) {
        t = false
      }
      if (this.coordinates_y > 65) {
        t = false
      }
      if (this.description === "") {
        t = false
        this.descriptionstatus = true
      }
      if (this.difficulty === "-") {
        t = false
        this.difficultystatus = true
      }
      if (this.discipline_name === "") {
        t = false
        this.discipline_namestatus = true
      }
      if (this.minimalPoint < 1) {
        t = false
      }
      if (this.averagePoint < 1) {
        t = false
      }
      if (this.person_name === "") {
        t = false
        this.person_namestatus = true
      }
      if (this.person_eyeColor === "-") {
        t = false
        this.person_eyeColorstatus = true
      }
      if (this.person_hairColor === "-") {
        t = false
        this.person_hairColorstatus = true
      }
      if (this.person_weight < 1) {
        t = false
      }
      if (this.person_nationality === "-") {
        t = false
        this.person_nationalitystatus = true
      }
      alert(t)
      return t
    },
    disciplineadd(){
      if(!this.disciplinestatus){
        return null
      } else {
        return {name:this.discipline_name,lectureHours:this.discipline_lectureHours}
      }
    },
    personadd(){
      if(!this.personstatus){
        return null
      } else {
        return {name: this.person_name,
          eyeColor: this.person_eyeColor,
          hairColor: this.person_hairColor,
          location: {x: this.person_location_x,
            y: this.person_location_y, z: this.person_location_z},
          weight: this.person_weight,
          nationality: this.person_nationality}
      }
    },
    close(){
      this.$router.push({name: 'main'});
    }
  },
  created(){
    //this.dot=(JSON.parse(sessionStorage.getItem('dot')))
    this.dot=Object.values(JSON.parse(localStorage.getItem('dot')))
    this.id=this.dot[0]
    this.name=this.dot[1]
    this.coordinates_id=Object.values(this.dot[2])[0]
    this.coordinates_x=Object.values(this.dot[2])[1]
    this.coordinates_y=Object.values(this.dot[2])[2]
    this.creation_date=this.dot[3]
    this.description=this.dot[4]
    this.difficulty=this.dot[5]
    alert(Object.values(this.dot[6]))
    this.discipline_id = Object.values(this.dot[6])[0]
    this.discipline_name = Object.values(this.dot[6])[1]
    this.discipline_lectureHours = Object.values(this.dot[6])[2]
    this.minimalPoint=this.dot[7]
    this.averagePoint=this.dot[8]
    let person=Object.values(this.dot[9])
    this.person_id=person[0]
    this.person_name=person[1]
    this.person_eyeColor=person[2]
    this.person_hairColor=person[3]
    this.person_location_id=Object.values(person[4])[0]
    this.person_location_x=Object.values(person[4])[1]
    this.person_location_y=Object.values(person[4])[2]
    this.person_location_z=Object.values(person[4])[3]
    this.person_weight=person[5]
    this.person_nationality=person[6]
    this.permission=this.dot[10]
    this.username=this.dot[11]
    this.disciplinestatus=(this.discipline_id!==0)
    this.personstatus=(this.person_id!==0)
  }
}
</script>

<style scoped>

</style>
