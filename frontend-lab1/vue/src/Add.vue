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
      <div>
        <input type="checkbox" v-model="permission">
      </div>
      <button type="submit" @click="add">Добавить лабораторную работу</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: 1,
      name: "",
      namestatus: false,
      coordinates_x: 0,
      coordinates_y: 0,
      description: "",
      descriptionstatus: false,
      difficulty: "-",
      difficultystatus: false,
      discipline_name: "",
      discipline_namestatus: false,
      discipline_lectureHours: 1,
      disciplinestatus: false,
      minimalPoint: 1,
      averagePoint: 1,
      person_name: "",
      person_namestatus: false,
      person_eyeColor: "-",
      person_eyeColorstatus: false,
      person_hairColor: "-",
      person_hairColorstatus: false,
      person_location_x: 1,
      person_location_y: 1,
      person_location_z: 1,
      person_weight: 1,
      person_nationality: "-",
      person_nationalitystatus: false,
      personstatus:false,
      dots: new Array(0),
      permission: true,
    }
  },
  methods: {
    add() {
      if (this.validat()) {
        alert(localStorage.getItem("jwt"))
        fetch("http://localhost:1298/api/LabWork/add", {
          method: "POST",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("jwt")
          }),
          body: JSON.stringify({
            name: this.name,
            coordinates:{x: this.coordinates_x, y: this.coordinates_y},
            description: this.description,
            difficulty: this.difficulty,
            discipline:this.disciplineadd(),
            minimalPoint: this.minimalPoint,
            averagePoint: this.averagePoint,
            person: this.personadd(),
            permission: this.permission
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
    validat(){
      alert(123131)
      var t=true
      if(this.name ===""){
        t=false
        this.namestatus=true
      }
      if(this.coordinates_x <-137){
        t=false
      }
      if(this.coordinates_y>65){
        t=false
      }
      if(this.description===""){
        t=false
        this.descriptionstatus=true
      }
      if(this.difficulty==="-"){
        t=false
        this.difficultystatus=true
      }
      if(this.discipline_name==="" && this.disciplinestatus){
        t=false
        this.discipline_namestatus=true
      }
      if (this.minimalPoint<1){
        t=false
      }
      if(this.averagePoint<1){
        t=false
      }
      if(this.person_name==="" && this.personstatus){
        t=false
        this.person_namestatus=true
      }
      if(this.person_hairColor==="-" && this.personstatus){
        t=false
        this.person_hairColorstatus=true
      }
      if(this.person_weight<1 && this.personstatus){
        t=false
      }
      if(this.person_nationality==="-" && this.personstatus){
        t=false
        this.person_nationalitystatus=true
      }
      if(this.person_location_x==="-" && this.personstatus){
        t=false
        this.person_nationalitystatus=true
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
  }
}
</script>
<style scoped>

</style>
