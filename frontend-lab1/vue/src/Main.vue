<template>
  <div>
    <div>
      <button v-if="role_admin_if()" type="submit" @click="role_admin_addAdmin">addAdmin</button>
      <button v-else type="submit" @click="role_admin_add">add</button>
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
    <div>
      <table>
        <thead>
          <th>name</th>
          <th>coordinates_x</th>
          <th>coordinates_y</th>
          <th>creationDate</th>
          <th>description</th>
          <th>difficulty</th>
          <th>discipline_name</th>
          <th>discipline_lectureHours</th>
          <th>minimalPoint</th>
          <th>averagePoint</th>
          <th>person_name</th>
          <th>person_eyeColor</th>
          <th>person_hairColor</th>
          <th>person_location_x</th>
          <th>person_location_y</th>
          <th>person_location_z</th>
          <th>person_weight</th>
          <th>person_nationality</th>
          <th>permission</th>
        </thead>
        <tbody>
          <tr v-for="dot in dots" :key="dot">
            <td>{{dot.name}}</td>
            <td>{{dot.coordinates.x}}</td>
            <td>{{dot.coordinates.y}}</td>
            <td>{{dot.creationDate}}</td>
            <td>{{dot.description}}</td>
            <td>{{dot.difficulty}}</td>
            <td>{{dot.discipline.name}}</td>
            <td>{{dot.discipline.lectureHours}}</td>
            <td>{{dot.minimalPoint}}</td>
            <td>{{dot.averagePoint}}</td>
            <td>{{dot.person.name}}</td>
            <td>{{dot.person.eyeColor}}</td>
            <td>{{dot.person.hairColor}}</td>
            <td>{{dot.person.location.x}}</td>
            <td>{{dot.person.location.y}}</td>
            <td>{{dot.person.location.z}}</td>
            <td>{{dot.person.weight}}</td>
            <td>{{dot.person.nationality}}</td>
            <td>{{dot.permission}}</td>
            <td><button type="submit"  v-if="makeUpdate(dot.username,dot.permission)" @click="update(dot.id,dot.coordinates.id,dot.discipline.id,dot.person)">Обновить</button></td>
            <td><button type="submit" v-if="makeUpdate(dot.username,dot.permission)" @click="del(dot.id)">Удалить</button></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <button type="submit" @click="filerbyminimalpoint(1)"></button>
      <button type="submit" @click="filerbyId(1,5)"></button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      id:1,
      name:"",
      namestatus:false,
      coordinates_x: 0,
      coordinates_y: 0,
      description: "",
      descriptionstatus: false,
      difficulty: "-",
      difficultystatus: false,
      discipline_name: "",
      discipline_namestatus: false,
      discipline_lectureHours: 1,
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
      dots: new Array(0),
      idRelatedLab: 1,
      permission:true
    }
  },
  methods:{
    add() {
      const routerData = this.$router.resolve({name: 'add'})
      window.open(routerData.href,"_blank")
    },
    update(id,coordinates_id,discipline_id,person){
      alert(coordinates_id)
      alert(discipline_id)
      fetch("http://localhost:1298/api/LabWork/update", {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        }),
        body: JSON.stringify({
          id: id,
          name: this.name,
          coordinates_id:coordinates_id,
          coordinates_x: this.coordinates_x,
          coordinates_y: this.coordinates_y,
          description: this.description,
          difficulty: this.difficulty,
          discipline_id:discipline_id,
          discipline_name: this.discipline_name,
          discipline_lectureHours: this.discipline_lectureHours,
          minimalPoint: this.minimalPoint,
          averagePoint: this.averagePoint,
          person_id: person.id,
          person_name: this.person_name,
          person_eyeColor: this.person_eyeColor,
          person_hairColor: this.person_hairColor,
          person_location_id: person.location.id,
          person_location_x: this.person_location_x,
          person_location_y: this.person_location_y,
          person_location_z: this.person_location_z,
          person_weight: this.person_weight,
          person_nationality: this.person_nationality,
          idRelatedLab: this.idRelatedLab,
          permission: this.permission
        })
      }).then((response) => {
        if(response.ok) {
          fetch("http://localhost:1298/api/LabWork/get", {
            method: "GET",
            headers: new Headers({
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem("jwt")
            })
          }).then(request => {
            alert(request)
            return request.json()
          }).then(request => {
              this.dots = request
            }
          )
        } else {
          alert("Неверно введённые данные")
        }
      })
    },
    del(id){
      fetch(`http://localhost:1298/api/LabWork/delete/${id}`, {
        method: "DELETE",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
        fetch("http://localhost:1298/api/LabWork/get", {
          method: "GET",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization':'Bearer ' + localStorage.getItem("jwt")
          })
        }).then(request =>
        {
          alert(request)
          return request.json()
        }).then(request =>
          {
            this.dots=request
          }
        )
      })
    },
    makeUpdate(user,permission){
      return localStorage.getItem("username") === user || (localStorage.getItem("role") === "admin" && permission)
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
      if(this.discipline_name===""){
        t=false
        this.discipline_namestatus=true
      }
      if (this.minimalPoint<1){
        t=false
      }
      if(this.averagePoint<1){
        t=false
      }
      if(this.person_name===""){
        t=false
        this.person_namestatus=true
      }
      if(this.person_eyeColor==="-"){
        t=false
        this.person_eyeColorstatus=true
      }
      if(this.person_hairColor==="-"){
        t=false
        this.person_hairColorstatus=true
      }
      if(this.person_weight<1){
        t=false
      }
      if(this.person_nationality==="-"){
        t=false
        this.person_nationalitystatus=true
      }
      alert(t)
      return t
    },
    role_admin_addAdmin() {
      if (localStorage.getItem("role") === "ROLE_ADMIN") {
        const routerData = this.$router.resolve({name: 'form'})
        window.open(routerData.href,"_blank")
      }
    },
    role_admin_add(){
      alert(localStorage.getItem('role'))
      fetch(`http://localhost:1298/api/admin/add/${localStorage.getItem("username")}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then((response) => {
        return response.text()
      })
    },
    role_admin_if(){
      return localStorage.getItem('role') === 'ROLE_ADMIN'
    },
    filerbyminimalpoint(minimalpoint){
      fetch(`http://localhost:1298/api/filter/deleteByMinimalPoint/${minimalpoint}`, {
        method: "DELETE",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
        fetch("http://localhost:1298/api/LabWork/get", {
          method: "GET",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization':'Bearer ' + localStorage.getItem("jwt")
          })
        }).then(request =>
        {
          alert(request)
          return request.json()
        }).then(request =>
          {
            this.dots=request
          }
        )
      })
    },
    filerbyId(min,max){
      fetch(`http://localhost:1298/api/filter/groupById`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        }),
        body: JSON.stringify({
          min:min,
          max:max
        })
      }).then(() => {
        fetch("http://localhost:1298/api/LabWork/get", {
          method: "GET",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization':'Bearer ' + localStorage.getItem("jwt")
          })
        }).then(request =>
        {
          alert(request)
          return request.json()
        }).then(request =>
          {
            this.dots=request
          }
        )
      })
    }
  },
  /*mounted: function (){
    window.setInterval(()=>{
      fetch("http://localhost:1298/api/LabWork/get", {
        method: "GET",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(request =>
      {
        return request.json()
      }).then(request =>
        {
          this.dots=request
        }
      )
    },2000)
  }*/
}
</script>

<style scoped>

</style>
