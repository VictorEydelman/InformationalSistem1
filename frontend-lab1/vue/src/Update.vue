<template>
  <div>
    <div style="margin-left: 80%; margin-top: 5%">
      <button type="submit" @click="close">Закрыть вкладку</button>
    </div>
    <table style="padding-left: 40%; margin-top: 5%">
      <div>
        <label>Name</label>
        <input type="text" v-model="name">
        <h7 v-if="namestatus && name===''" style="color: red">Введены недопустимые данные</h7>
      </div>
      <div>
        <label>Coordinates</label>
        <p>Выбрать координату из существующих <input type="checkbox" v-model="coordinateshave"></p>
        <div v-if="coordinateshave">
          <select v-model="coordinates" @change="coordinates_x=coordinates.split(' ')[3];
            coordinates_y=coordinates.split(' ')[5]">
            <option v-for="dot in dotsCreate" :key="dot">
              ID: {{dot.id}} X: {{dot.coordinates.x}}  Y: {{dot.coordinates.y}}
            </option>
          </select>
        </div>
        <div v-else>
          <div class="tab">
            <label>X</label>
            <input type="number" v-model="coordinates_x">
            <h7 v-if="coordinates_x<-137" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Y</label>
            <input type="number" v-model="coordinates_y">
            <h7 v-if="coordinates_y>65" style="color: red">Введены недопустимые данные</h7>
          </div>
        </div>
      </div>
      <div>
        <label>Description</label>
        <input type="text" v-model="description">
        <h7 v-if="descriptionstatus && description===''" style="color: red">Введены недопустимые данные</h7>
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
        <h7 v-if="difficultystatus && difficulty==='-'" style="color: red">Введены недопустимые данные</h7>
      </div>
      <label>Discipline</label>
      <p>Дисциплина не равна NULL<input type="checkbox" v-model="disciplinestatus"></p>
      <div v-if="disciplinestatus">
        <p>Выбрать дисциплину из существующих <input type="checkbox" v-model="disciplinehave"></p>
        <div v-if="disciplinehave">
          <select v-model="discipline" @change="discipline_name=discipline.split(' ')[3];
            discipline_lectureHours=discipline.split(' ')[5]">
            <option v-if="dot.discipline.name!==''" v-for="dot in dotsCreate" :key="dot">
              ID: {{dot.id}} Name: {{dot.discipline.name}}  LectureHours: {{dot.discipline.lectureHours}}
            </option>
          </select>
        </div>
        <div v-else>
          <div class="tab">
            <label>Name</label>
            <input type="text" v-model="discipline_name">
            <h7 v-if="discipline_namestatus && discipline_name===''" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Lecture Hours</label>
            <input type="number" v-model="discipline_lectureHours">
          </div>
        </div>
      </div>
      <div>
        <label>Minimal Point</label>
        <input type="number" v-model="minimalPoint">
        <h7 v-if="minimalPoint<1" style="color: red">Введены недопустимые данные</h7>
      </div>
      <div>
        <label>Average Point</label>
        <input type="number" v-model="averagePoint">
        <h7 v-if="averagePoint<1" style="color: red">Введены недопустимые данные</h7>
      </div>
      <label>Person</label>
      <p>Person не равна NULL<input type="checkbox" v-model="personstatus"></p>
      <div v-if="personstatus">
        <p>Выбрать person из существующих <input type="checkbox" v-model="personhave"></p>
        <div v-if="personhave">
          <select v-model="person" @change="person_name=person.split(' ')[3];
            person_eyeColor=person.split(' ')[5]; person_hairColor=person.split(' ')[7]; person_location_x=person.split(' ')[9];
            person_location_y=person.split(' ')[11]; person_location_z=person.split(' ')[13]; person_weight=person.split(' ')[15];
            person_nationality=person.split(' ')[17]">
            <option v-if="dot.person.name!==''" v-for="dot in dotsCreate" :key="dot">
              ID: {{dot.id}} Name: {{dot.person.name}} LectureHours: {{dot.person.eyeColor}} HairColor: {{dot.person.hairColor}}
              Location_X: {{dot.person.location.x}} Location_Y: {{dot.person.location.y}} Location_Z: {{dot.person.location.z}}
              Weight: {{dot.person.weight}} Nationality: {{dot.person.nationality}}
            </option>
          </select>
        </div>
        <div v-else>
          <div class="tab">
            <label>Name</label>
            <input type="text" v-model="person_name">
            <h7 v-if="person_namestatus && person_name===''" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Eye color</label>
            <select v-model="person_eyeColor">
              <option value="-">-</option>
              <option>GREEN</option>
              <option>RED</option>
              <option>BROWN</option>
            </select>
            <h7 v-if="person_eyeColorstatus && person_eyeColor==='-'" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Hair Color</label>
            <select v-model="person_hairColor">
              <option value="-">-</option>
              <option>GREEN</option>
              <option>RED</option>
              <option>BROWN</option>
            </select>
            <h7 v-if="person_hairColorstatus && person_hairColor==='-'" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Location</label>
            <p>Выбрать локация из существующих <input type="checkbox" v-model="locationhave"></p>
            <div v-if="locationhave">
              <select v-model="locat" @change="person_location_x=locat.split(' ')[3];
                person_location_y=locat.split(' ')[5]; person_location_z=locat.split(' ')[7]">
                <option v-for="dot in dotsCreate" :key="dot">
                  ID: {{dot.id}} X: {{dot.person.location.x}} Y: {{dot.person.location.y}} Z: {{dot.person.location.z}}
                </option>
              </select>
            </div>
            <div v-else>
              <div class="tab">
                <label>X</label>
                <input type="number" v-model="person_location_x">
              </div>
              <div class="tab">
                <label>Y</label>
                <input type="number" v-model="person_location_y">
              </div>
              <div class="tab">
                <label>Z</label>
                <input type="number" v-model="person_location_z">
              </div>
            </div>
          </div>
          <div class="tab">
            <label>Weight</label>
            <input type="number" v-model="person_weight">
            <h7 v-if="person_weight<1" style="color: red">Введены недопустимые данные</h7>
          </div>
          <div class="tab">
            <label>Nationality</label>
            <select v-model="person_nationality">
              <option value="-">-</option>
              <option>CHINA</option>
              <option>VATICAN</option>
              <option>SOUTH_KOREA</option>
            </select>
            <h7 v-if="person_nationalitystatus && person_nationality==='-'" style="color: red">Введены недопустимые данные</h7>
          </div>
        </div>
      </div>
      <button type="submit" @click="update()">Обновить лабораторную работу</button>
    </table>
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
      personstatus:false,
      coordinates:"",
      coordinateshave:false,
      discipline:"",
      disciplinehave:false,
      person:"",
      personhave:false,
      locat:"",
      locationhave:false,
      dotsCreate:new Array(0)
    }
  },
  methods: {
    update() {
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
      if(this.person_eyeColor==="-"){
        return {name: this.person_name,
          eyeColor: null,
          hairColor: this.person_hairColor,
          location: {x: this.person_location_x,
            y: this.person_location_y, z: this.person_location_z},
          weight: this.person_weight,
          nationality: this.person_nationality}
      } else {
        return {
          name: this.person_name,
          eyeColor: this.person_eyeColor,
          hairColor: this.person_hairColor,
          location: {
            x: this.person_location_x,
            y: this.person_location_y, z: this.person_location_z
          },
          weight: this.person_weight,
          nationality: this.person_nationality
        }
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
    this.discipline_id = Object.values(this.dot[6])[0]

    this.discipline_name = Object.values(this.dot[6])[1]
    if(this.discipline_id===0){
      this.discipline_lectureHours = 0
    } else {
      this.discipline_lectureHours = Object.values(this.dot[6])[2]
    }
    this.minimalPoint=this.dot[7]
    this.averagePoint=this.dot[8]
    let person=Object.values(this.dot[9])
    this.person_id=person[0]
    this.person_name=person[1]
    this.person_eyeColor=person[2]
    this.person_hairColor=person[3]
    this.person_location_id = Object.values(person[4])[0]
    if(this.person_id!==0) {
      this.person_location_x = Object.values(person[4])[1]
      this.person_location_y = Object.values(person[4])[2]
      this.person_location_z = Object.values(person[4])[3]
      this.person_weight = person[5]
    } else {
      this.person_location_x = 0
      this.person_location_y = 0
      this.person_location_z = 0
      this.person_weight = 1
    }
    this.person_nationality=person[6]
    this.permission=this.dot[10]
    this.username=this.dot[11]
    this.disciplinestatus=(this.discipline_id!==0)
    this.personstatus=(this.person_id!==0)
    this.dotsCreate=Object.values(JSON.parse(localStorage.getItem("dots")))
  }
}
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
input[type="number"],
select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button[type="submit"]:hover {
  background-color: #45a049;
}

span {
  font-size: 12px;
}

span[style*="color: red"] {
  color: red;
}
.tab{
  padding-left: 10%;
}
</style>
