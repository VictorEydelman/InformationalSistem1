<template>
  <div>
    <div>
      <button v-if="role_admin_if()" type="submit" @click="role_admin_addAdmin">addAdmin</button>
      <button v-else type="submit" @click="role_admin_add">add</button>
      <button type="submit" @click="filerbyminimalpoint(1)"></button>
      <button type="submit" @click="filerbyId"></button>
      <button type="submit" @click="filterByDescription"></button>
    </div>
    <div>
      <button type="submit" @click="add">Добавить лабораторную работу</button>
    </div>
    <div>
      <table>
        <th><input style="width: 50px" type="text" v-model="nameFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="coordinates_xFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="coordinates_yFilter"></th>
        <th><input style="width: 50px" type="text" v-model="descriptionFilter"></th>
        <th><select style="width: 50px" v-model="difficultyFilter">
          <option value="-">-</option>
          <option>EASY</option>
          <option>NORMAL</option>
          <option>HARD</option>
          <option>VERY_HARD</option>
          <option>IMPOSSIBLE</option>
        </select></th>
        <th><input style="width: 50px" type="text" v-model="discipline_nameFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="discipline_lectureHoursFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="minimalPointFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="averagePointFilter"></th>
        <th><input style="width: 50px" type="text" v-model="person_nameFilter"></th>
        <th><select style="width: 50px" v-model="person_eyeColorFilter">
          <option value="-">-</option>
          <option>GREEN</option>
          <option>RED</option>
          <option>BROWN</option>
        </select></th>
        <th><select style="width: 50px" v-model="person_hairColorFilter">
          <option value="-">-</option>
          <option>GREEN</option>
          <option>RED</option>
          <option>BROWN</option>
        </select></th>
        <th><input style="width: 50px" type="number" v-model.number="person_location_xFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="person_location_yFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="person_location_zFilter"></th>
        <th><input style="width: 50px" type="number" v-model.number="person_weightFilter"></th>
        <th><select style="width: 50px" v-model="person_nationalityFilter">
          <option value="-">-</option>
          <option>CHINA</option>
          <option>VATICAN</option>
          <option>SOUTH_KOREA</option>
        </select></th>
        <th><select style="width: 50px" v-model="permissionFilter">
          <option value="-">-</option>
          <option>true</option>
          <option>false</option>
        </select></th>
      </table>
      <table border="1">
        <thead>
          <tr>
          <th rowspan="3">name</th>
          <th rowspan="2" colspan="2">coordinates</th>
          <th rowspan="3">creationDate</th>
          <th rowspan="3">description</th>
          <th rowspan="3">difficulty</th>
          <th rowspan="2" colspan="2">discipline</th>
          <th rowspan="3">minimal Point</th>
          <th rowspan="3">average Point</th>
          <th colspan="8">person</th>
          </tr>
        <tr>
          <th rowspan="2">name</th>
          <th rowspan="2">eyeColor</th>
          <th rowspan="2">hairColor</th>
          <th colspan="3">location</th>
          <th rowspan="2">weight</th>
          <th rowspan="2">nationality</th>
        </tr>
        <tr>
          <th>x</th>
          <th>y</th>
          <th>name</th>
          <th>lecture Hours</th>
          <th>x</th>
          <th>y</th>
          <th>z</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="dot in filteredEmployees" :key="dot">
            <td>{{dot.name}}</td>
            <td>{{dot.coordinates.x}}</td>
            <td>{{dot.coordinates.y}}</td>
            <td>{{formateDate(dot.creationDate)}}</td>
            <td>{{dot.description}}</td>
            <td>{{dot.difficulty}}</td>
            <td v-if="dot.discipline.name!=='-'" >{{dot.discipline.name}}</td>
            <td v-if="dot.discipline.name!=='-'">{{dot.discipline.lectureHours}}</td>
            <td v-else colspan="2">{{dot.discipline.name}}</td>
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
            <td><button type="submit" v-if="makeUpdate(dot.username,dot.permission)" @click="update(dot)">Обновить</button>
            <button type="submit" v-if="makeUpdate(dot.username,dot.permission)" @click="del(dot.id)">Удалить</button>
              <div>
                <input type="number" v-if="makeUpdate(dot.username,dot.permission)" v-model="dot.step">
                <button type="submit" v-if="makeUpdate(dot.username,dot.permission)" @click="upTheDifficulty(dot.id,dot.step)"></button>
              </div>
              <button type="submit" v-if="makeUpdate(dot.username,dot.permission)" @click="DeleteFromDiscipline(dot.id)">sdfdf</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>

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
      permission:true,
      nameFilter:"",
      coordinates_xFilter: null,
      coordinates_yFilter: null,
      descriptionFilter: null,
      difficultyFilter: "-",
      discipline_nameFilter: null,
      discipline_lectureHoursFilter: null,
      minimalPointFilter: null,
      averagePointFilter: null,
      person_nameFilter: null,
      person_eyeColorFilter: "-",
      person_hairColorFilter: "-",
      person_location_xFilter: null,
      person_location_yFilter: null,
      person_location_zFilter: null,
      person_weightFilter: null,
      person_nationalityFilter: "-",
      permissionFilter: "-",
    }
  },
  methods:{
    add() {
      const routerData = this.$router.resolve({name: 'add'})
      window.open(routerData.href,"_blank")
    },
    update(dot){
      alert(JSON.stringify(dot))
      sessionStorage.setItem("dot",JSON.stringify(dot))
      const routerData = this.$router.resolve({name: 'update'})
      window.open(routerData.href,"_blank")
    },
    del(id){
      fetch(`http://localhost:1298/api/LabWork/delete/${id}`, {
        method: "DELETE",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    makeUpdate(user,permission){
      return localStorage.getItem("username") === user || (localStorage.getItem("role") === "admin" && permission)
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
    filerbyId(){
      const routerData = this.$router.resolve({name: 'index'})
      window.open(routerData.href,"_blank")
    },
    filterByDescription(){
      const routerData = this.$router.resolve({name: 'description'})
      window.open(routerData.href,"_blank")
    },
    upTheDifficulty(id, step){
      fetch(`http://localhost:1298/api/filter/upTheDifficulty/${id}/${step}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    DeleteFromDiscipline(id){
      alert(1)
      fetch(`http://localhost:1298/api/filter/deleteFromDiscipline/${id}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization':'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    formateDate(dateString){
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit'};
      const date = new Date(dateString);
      return date.toLocaleString('ru-RU', options);
    },
    checkdiscipline(disc){
      if(disc==null){
      }
    }
  },
  mounted: function (){
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
    },10000)
  },
  computed: {
    filteredEmployees() {
      return this.dots.filter(dot => {
        if(dot.discipline==null){
          dot.discipline={name:"-",lectureHours:-1}
        }
        let t=true
        if(this.nameFilter!=="" && dot.name!==this.nameFilter){
          t=false
        }
        if(this.coordinates_xFilter!==null && this.coordinates_xFilter!=="" && dot.coordinates.x!==this.coordinates_xFilter){
          t=false
        }
        if(this.coordinates_yFilter!==null && this.coordinates_yFilter!=="" && dot.coordinates.y!==this.coordinates_yFilter){
          t=false
        }
        if(this.difficultyFilter!=="-" && dot.difficulty!==this.difficultyFilter){
          t=false
        }
        if(this.descriptionFilter!==null && this.descriptionFilter!=="" && dot.description!==this.descriptionFilter){
          t=false
        }
        if(this.discipline_nameFilter!==null && this.discipline_nameFilter!=="" && dot.discipline.name!==this.discipline_nameFilter){
          t=false
        }
        if(this.discipline_lectureHoursFilter!==null && this.discipline_lectureHoursFilter!=="" && dot.discipline.lectureHours!==this.discipline_lectureHoursFilter){
          t=false
        }
        console.log(t)
        if(this.minimalPointFilter!==null && this.minimalPointFilter!=="" && dot.minimalPoint!==this.minimalPointFilter){
          t=false
        }
        if(this.averagePointFilter!==null && this.averagePointFilter!=="" && dot.averagePoint!==this.averagePointFilter){
          t=false
        }
        if(this.person_nameFilter!==null && this.person_nameFilter!=="" && dot.person.name!==this.person_nameFilter){
          t=false
        }
        if(this.person_eyeColorFilter!=="-" && dot.person.eyeColor!==this.person_eyeColorFilter){
          t=false
        }
        if(this.person_hairColorFilter!=="-" && dot.person.hairColor!==this.person_hairColorFilter){
          t=false
        }

        if(this.person_location_xFilter!==null && this.person_location_xFilter!=="" && dot.person.location.x!==this.person_location_xFilter){
          t=false
        }
        if(this.person_location_yFilter!==null && this.person_location_yFilter!=="" && dot.person.location.y!==this.person_location_yFilter){
          t=false
        }
        if(this.person_location_zFilter!==null && this.person_location_zFilter!=="" && dot.person.location.z!==this.person_location_zFilter){
          t=false
        }
        if(this.person_weightFilter!==null && this.person_weightFilter!=="" && dot.person.weight!==this.person_weightFilter){
          t=false
        }
        if(this.person_nationalityFilter!=="-" && dot.person.nationality!==this.person_nationalityFilter){
          t=false
        }
        let per=dot.permission ? "true" : "false"
        if(this.permissionFilter!=="-" && per!==this.permissionFilter){
          t=false
        }
        console.log(t)
        return t
      });
    },
  },
}
</script>

<style scoped>
table{
  width: 50%;
}
th,td{
  width: 50px;
}
</style>
