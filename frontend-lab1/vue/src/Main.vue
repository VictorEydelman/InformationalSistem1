<template>
  <div>
    <table>
      <tr>
      <td style="padding-left: 0px; border: aliceblue">
        <button id="sad" style="margin-right: 20%" v-if="role_admin_if()" type="submit" @click="role_admin_addAdmin">Добавление новых администраторов</button>
        <button id="sad" style="margin-right: 20%" v-else type="submit" @click="role_admin_add">Подать заявку на роль администратора</button>
      </td>
      <td style="margin-right: 5%; border: aliceblue">
        {{this.username}}
        <button style="margin-left: 5%" type="submit" @click="close">Выйти</button>
      </td>
      </tr>
    </table>
    <div style="margin-top: 70px">
      <table>
        <tr>
          <td><input type="number" v-model="filerminimalpoint">
          <button type="submit" @click="filerbyminimalpoint()">Удалить любую лабораторную по минимальному значению</button></td>
          <td><button type="submit" @click="filerbyId">Группировка по Id</button></td>
          <td><h4>Фильтр по description</h4><input v-model="descriptionFilterServ" @input="filter"></td>
        </tr>
      </table>
    </div>
    <div>
      <button style="margin-left: 40%; margin-right: -40%;" type="submit" @click="add">Добавить лабораторную работу</button>
    </div>
    <div>
      <table class="contain"  style="font-size: 90%">
        <thead class="containhead" id="frame">
          <tr>
            <th rowspan="3" @click="sortTable('id')">id
              <i v-if="sortKey === 'id'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
              <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
              <input style="width: 30px" type="number" v-model.number="idFilter"></th>
            <th rowspan="3" @click="sortTable('name')">name
              <i v-if="sortKey === 'name'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
              <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
              <input style="width: 35px" type="text" v-model="nameFilter"></th>
          <th rowspan="2" colspan="2">coordinates</th>
          <th rowspan="3" @click="sortTable('creationDate')" style="">creationDate
            <i v-if="sortKey === 'creationDate'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 35px" type="text" v-model="creationDateFilter"></th>
          <th rowspan="3" @click="sortTable('description')">description
            <i v-if="sortKey === 'description'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 50px" type="text" v-model="descriptionFilter"></th>
          <th rowspan="3" @click="sortTable('difficulty')">difficulty
            <i v-if="sortKey === 'difficulty'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <select style="width: 50px" v-model="difficultyFilter">
            <option value="-">-</option>
            <option>EASY</option>
            <option>NORMAL</option>
            <option>HARD</option>
            <option>VERY_HARD</option>
            <option>IMPOSSIBLE</option>
          </select></th>
          <th rowspan="2" colspan="2">discipline<select style="width: 50px" v-model="disciplineFilter">
            <option value="-">-</option>
            <option>null</option>
            <option>not null</option>
          </select></th>
          <th rowspan="3" @click="sortTable('minimalPoint')">minimal Point
            <i v-if="sortKey === 'minimalPoint'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 50px" type="number" v-model.number="minimalPointFilter"></th>
          <th rowspan="3" @click="sortTable('averagePoint')">average Point
            <i v-if="sortKey === 'averagePoint'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 50px" type="number" v-model.number="averagePointFilter"></th>
          <th colspan="8">person<select style="width: 50px" v-model="personFilter">
            <option value="-">-</option>
            <option>null</option>
            <option>not null</option>
          </select></th>
            <th rowspan="3">Операции</th>
          </tr>
        <tr>
          <th rowspan="2" @click="sortTable('person name')">name
            <i v-if="sortKey === 'person name'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 35px" type="text" v-model="person_nameFilter"></th>
          <th rowspan="2" @click="sortTable('person eyeColor')">eyeColor
            <i v-if="sortKey === 'person eyeColor'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <select style="width: 50px" v-model="person_eyeColorFilter">
            <option value="-">-</option>
            <option>GREEN</option>
            <option>RED</option>
            <option>BROWN</option>
          </select></th>
          <th rowspan="2" @click="sortTable('person hairColor')">hairColor
            <i v-if="sortKey === 'person hairColor'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <select style="width: 50px" v-model="person_hairColorFilter">
            <option value="-">-</option>
            <option>GREEN</option>
            <option>RED</option>
            <option>BROWN</option>
          </select></th>
          <th colspan="3">location</th>
          <th rowspan="2" @click="sortTable('person weight')">weight
            <i v-if="sortKey === 'person weight'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 40px" type="number" v-model.number="person_weightFilter"></th>
          <th rowspan="2" @click="sortTable('person nationality')">nationality
            <i v-if="sortKey === 'person nationality'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <select style="width: 50px" v-model="person_nationalityFilter">
            <option value="-">-</option>
            <option>CHINA</option>
            <option>VATICAN</option>
            <option>SOUTH_KOREA</option>
          </select></th>
        </tr>
        <tr>
          <th @click="sortTable('coordinates x')">x
            <i v-if="sortKey === 'coordinates x'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 30px" type="number" v-model.number="coordinates_xFilter"></th>
          <th @click="sortTable('coordinates y')">y
            <i v-if="sortKey === 'coordinates y'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 30px" type="number" v-model.number="coordinates_yFilter"></th>
          <th @click="sortTable('discipline name')">name
            <i v-if="sortKey === 'discipline name'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 35px" type="text" v-model="discipline_nameFilter"></th>
          <th @click="sortTable('discipline lectureHours')">lecture Hours
            <i v-if="sortKey === 'discipline lectureHours'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 25px; margin-bottom: 10%" type="number" v-model.number="minimalPointFilter"></th>
          <th @click="sortTable('person location x')">x
            <i v-if="sortKey === 'person location x'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 30px" type="number" v-model.number="person_location_xFilter"></th>
          <th @click="sortTable('person location y')">y
            <i v-if="sortKey === 'person location y'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 30px" type="number" v-model.number="person_location_yFilter"></th>
          <th @click="sortTable('person location z')">z
            <i v-if="sortKey === 'person location z'" style="font-size: 50%" :class="this.sortOrder===1 ?'fas fa-arrow-down': 'fas fa-arrow-up'" aria-hidden="true"></i>
            <i v-else style="font-size: 50%" :class="'fas fa-arrows-alt-v'"></i>
            <input style="width: 30px" type="number" v-model.number="person_location_zFilter">
          </th>
        </tr>
        </thead>
        <tbody  class="contain" id="frame">
          <tr v-for="dot in paginatedData" :key="dot">
            <td>{{dot.id}}</td>
            <td>{{dot.name}}</td>
            <td>{{dot.coordinates.x}}</td>
            <td>{{dot.coordinates.y}}</td>
            <td>{{formateDate(dot.creationDate)}}</td>
            <td>{{dot.description}}</td>
            <td>{{dot.difficulty}}</td>
            <td v-if="dot.discipline.name!==''" >{{dot.discipline.name}}</td>
            <td v-if="dot.discipline.name!==''">{{dot.discipline.lectureHours}}</td>
            <td v-else colspan="2">-</td>
            <td>{{dot.minimalPoint}}</td>
            <td>{{dot.averagePoint}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.name}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.eyeColor}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.hairColor}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.location.x}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.location.y}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.location.z}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.weight}}</td>
            <td v-if="dot.person.name!==''">{{dot.person.nationality}}</td>
            <td v-else colspan="8">-</td>
            <td><button type="submit" class="tabbutton" :disabled="!makeUpdate(dot.username,dot.permission)" @click="update(dot)">Обновить</button>
            <button type="submit" class="tabbutton" :disabled="!makeUpdate(dot.username,dot.permission)" @click="del(dot.id)">Удалить</button>
              <div >
                <input type="number" class="tabbutton" :disabled="!makeUpdate(dot.username,dot.permission)" v-model="dot.step">
                <button type="submit" class="tabbutton" :disabled="!makeUpdate(dot.username,dot.permission)" @click="upTheDifficulty(dot.id,dot.step)">Поднять сложность</button>
              </div>
              <button type="submit"  class="tabbutton"  :disabled="!makeUpdate(dot.username,dot.permission)" @click="DeleteFromDiscipline(dot.id)">Удалить из дисциплины</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <button @click="prevPage" :disabled="currentPage===1">Предыдущая страница</button>
        <span>Страница {{currentPage}} из {{totalPages}}. Вывод по </span>
        <input style="width: 50px" type="number" v-model.number="itemsPerPage">
        <span> объектов</span>
        <button @click="nextPage" :disabled="currentPage===totalPages">Следующая страница</button>
      </div>
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
      permission: true,
      idFilter: null,
      nameFilter: "",
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
      filerminimalpoint: 0,
      username: localStorage.getItem("username"),
      sortKey: '',
      sortOrder: 1,
      disciplineFilter: "-",
      personFilter: "-",
      currentPage: 1,
      itemsPerPage: 5,
      descriptionFilterServ: "",
      creationDateFilter: ""
    }
  },
  methods: {
    add() {
      localStorage.setItem("dots", JSON.stringify(this.dots))
      const routerData = this.$router.resolve({name: 'add'})
      window.open(routerData.href, "_blank")
    },
    update(dot) {
      localStorage.setItem("dot", JSON.stringify(dot))
      localStorage.setItem("dots", JSON.stringify(this.dots))
      const routerData = this.$router.resolve({name: 'update'})
      window.open(routerData.href, "_blank")
    },
    del(id) {
      fetch(`http://localhost:1298/api/LabWork/delete/${id}`, {
        method: "DELETE",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    makeUpdate(user, permission) {
      return localStorage.getItem("username") === user || (localStorage.getItem("role") === "admin" && permission)
    },
    role_admin_addAdmin() {
      if (localStorage.getItem("role") === "ROLE_ADMIN") {
        const routerData = this.$router.resolve({name: 'form'})
        window.open(routerData.href, "_blank")
      }
    },
    role_admin_add() {
      fetch(`http://localhost:1298/api/admin/add/${localStorage.getItem("username")}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then((response) => {
        return response.text()
      })
    },
    role_admin_if() {
      return localStorage.getItem('role') === 'ROLE_ADMIN'
    },
    filerbyminimalpoint() {
      fetch(`http://localhost:1298/api/filter/deleteByMinimalPoint/${this.filerminimalpoint}`, {
        method: "DELETE",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    filerbyId() {
      const routerData = this.$router.resolve({name: 'index'})
      window.open(routerData.href, "_blank")
    },
    filterByDescription() {
      const routerData = this.$router.resolve({name: 'description'})
      window.open(routerData.href, "_blank")
    },
    upTheDifficulty(id, step) {
      fetch(`http://localhost:1298/api/filter/upTheDifficulty/${id}/${step}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    DeleteFromDiscipline(id) {
      fetch(`http://localhost:1298/api/filter/deleteFromDiscipline/${id}`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(() => {
      })
    },
    formateDate(dateString) {
      const options = {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      };
      const date = new Date(dateString);
      return date.toLocaleString('ru-RU', options);
    },
    close() {
      this.$router.push({name: 'first'});
      localStorage.setItem("jwt", "")
      localStorage.setItem("username", "")
      localStorage.setItem("role", "")
    },
    sortTable(key) {
      this.sortOrder = this.sortKey === key ? -this.sortOrder : 1;
      this.sortKey = key;
    },
    sortedDif(a, b) {
      let n = {"EASY": 1, "NORMAL": 2, "HARD": 3, "VERY_HARD": 4, "IMPOSSIBLE": 5}
      return n[a] > n[b]
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--
      }
    },
    filter() {
      fetch(`http://localhost:1298/api/filter/getByDescription/${this.descriptionFilterServ}`, {
        method: "GET",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(request => {
        return request.json()
      }).then(request => {
          this.dots = request
        }
      )
    }
  },
  mounted: function () {
    window.setInterval(() => {
      if (this.descriptionFilterServ === "") {
        fetch("http://localhost:1298/api/LabWork/get", {
          method: "GET",
          headers: new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("jwt")
          })
        }).then(request => {
          return request.json()
        }).then(request => {
            this.dots = request
          }
        )
      } else {
        this.filter()
      }
    }, 3000)
  },
  created() {
    fetch("http://localhost:1298/api/LabWork/get", {
      method: "GET",
      headers: new Headers({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem("jwt")
      })
    }).then(request => {
      return request.json()
    }).then(request => {
        this.dots = request
      }
    )
  },
  computed: {
    filteredEmployees() {
      return this.dots.filter(dot => {
        if (dot.discipline == null) {
          dot.discipline = {id: 0, name: "", lectureHours: -1000000}
        }
        if (dot.person == null) {
          dot.person = {
            id: 0, name: "", eyeColor: "", hairColor: "", location: {id: 0, x: -10000000, y: -1000000, z: -1000000},
            weight: -1000000000, nationality: ""
          }
        }
        if (dot.person.eyeColor === null) {
          dot.person.eyeColor = "-"
        }
        let t = true
        if (this.idFilter !== null && this.idFilter !== "" && dot.id !== this.idFilter) {
          t = false
        }
        if (this.nameFilter !== "" && dot.name !== this.nameFilter) {
          t = false
        }
        if (this.coordinates_xFilter !== null && this.coordinates_xFilter !== "" && dot.coordinates.x !== this.coordinates_xFilter) {
          t = false
        }
        if (this.coordinates_yFilter !== null && this.coordinates_yFilter !== "" && dot.coordinates.y !== this.coordinates_yFilter) {
          t = false
        }
        if (this.creationDateFilter !== "" && this.formateDate(dot.creationDate) !== this.creationDateFilter) {
          t = false
        }
        if (this.difficultyFilter !== "-" && dot.difficulty !== this.difficultyFilter) {
          t = false
        }
        if (this.descriptionFilter !== null && this.descriptionFilter !== "" && dot.description !== this.descriptionFilter) {
          t = false
        }
        let dis = (dot.discipline.id === 0) ? "null" : "not null"
        if (this.disciplineFilter !== "-" && dis !== this.disciplineFilter) {
          t = false
        }
        if (this.discipline_nameFilter !== null && this.discipline_nameFilter !== "" && dot.discipline.name !== this.discipline_nameFilter) {
          t = false
        }
        if (this.discipline_lectureHoursFilter !== null && this.discipline_lectureHoursFilter !== "" && dot.discipline.lectureHours !== this.discipline_lectureHoursFilter) {
          t = false
        }
        if (this.minimalPointFilter !== null && this.minimalPointFilter !== "" && dot.minimalPoint !== this.minimalPointFilter) {
          t = false
        }
        if (this.averagePointFilter !== null && this.averagePointFilter !== "" && dot.averagePoint !== this.averagePointFilter) {
          t = false
        }
        let pers = (dot.person.id === 0) ? "null" : "not null"
        if (this.personFilter !== "-" && pers !== this.personFilter) {
          t = false
        }
        if (this.person_nameFilter !== null && this.person_nameFilter !== "" && dot.person.name !== this.person_nameFilter) {
          t = false
        }
        if (this.person_eyeColorFilter !== "-" && dot.person.eyeColor !== this.person_eyeColorFilter) {
          t = false
        }
        if (this.person_hairColorFilter !== "-" && dot.person.hairColor !== this.person_hairColorFilter) {
          t = false
        }

        if (this.person_location_xFilter !== null && this.person_location_xFilter !== "" && dot.person.location.x !== this.person_location_xFilter) {
          t = false
        }
        if (this.person_location_yFilter !== null && this.person_location_yFilter !== "" && dot.person.location.y !== this.person_location_yFilter) {
          t = false
        }
        if (this.person_location_zFilter !== null && this.person_location_zFilter !== "" && dot.person.location.z !== this.person_location_zFilter) {
          t = false
        }
        if (this.person_weightFilter !== null && this.person_weightFilter !== "" && dot.person.weight !== this.person_weightFilter) {
          t = false
        }
        if (this.person_nationalityFilter !== "-" && dot.person.nationality !== this.person_nationalityFilter) {
          t = false
        }
        let per = dot.permission ? "true" : "false"
        if (this.permissionFilter !== "-" && per !== this.permissionFilter) {
          t = false
        }
        return t
      });
    },
    totalPages() {
      return Math.ceil(this.sortedData.length / this.itemsPerPage)
    },
    paginatedData() {
      const start = (this.currentPage - 1) * this.itemsPerPage
      const end = start + this.itemsPerPage
      return this.sortedData.slice(start, end)
    },
    sortedData() {
      return this.filteredEmployees.slice().sort((a, b) => {
        if (this.sortKey) {
          let c = this.sortKey.split(" ")
          const modifier = this.sortOrder;
          if (c[0] === "difficulty") {
            if (this.sortedDif(b[c[0]], a[c[0]])) return -1 * modifier;
            if (this.sortedDif(a[c[0]], b[c[0]])) return modifier;
          } else {
            if (c.length === 1) {
              if (a[c[0]] < b[c[0]]) return -1 * modifier;
              if (a[c[0]] > b[c[0]]) return modifier;
            } else if (c.length === 2) {
              if (a[c[0]][c[1]] < b[c[0]][c[1]]) return -1 * modifier;
              if (a[c[0]][c[1]] > b[c[0]][c[1]]) return modifier;
            } else {
              if (a[c[0]][c[1]][c[2]] < b[c[0]][c[1]][c[2]]) return -1 * modifier;
              if (a[c[0]][c[1]][c[2]] > b[c[0]][c[1]][c[2]]) return modifier;
            }
          }
          return 0;
        }
        return 0;
      });
    },

  },
}
</script>

<style scoped>


#frame{
  border-radius: 50px;
  box-shadow: 0 0 0 4px #968089, 2px 1px 6px 4px rgba(150, 128, 137, 0.5);
}
button:hover{
  color: red
}
::selection{
  color: white;
  background-color: black;
}
input option{
  text-align: center;
}
frame{
  border-bottom:100px;
}

.containhead{
   margin-top: 1%;
   text-align: center;
   vertical-align:middle;
   background: rgba(168, 75, 15, 0.96);
 }

#frame{
  border-radius: 1px;
  box-shadow: 0 0 0 4px #968089, 1px 1px 1px 1px rgba(150, 128, 137, 0.5);
}
::selection{
  color: white;
  background-color: black;
}

input option{
  text-align: center;
}
frame{
  border-bottom:100px;
}
input{
  font-size: 80%;
}
.contain {
  width: 50%;
  margin-top: 1%;
  text-align: center;
  vertical-align:middle;
  background: rgba(196, 119, 23, 0.96);
  max-width: 50%;
  overflow-x: auto;
}

table {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}
th, td {
  padding: 2px;
  text-align: center;
  border: 1px solid #ddd;
  width: 50px;
}
th {
  background-color: #f2f2f2;
  cursor: pointer;
}
th:hover {
  background-color: #e0e0e0;
}
tr:nth-child(even) {
  background-color: #f9f9f9;
}
button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 8px 12px;
  text-decoration: none;
  margin: 4px 10px;
  cursor: pointer;
  border-radius: 4px;
}
button:disabled {
  background-color: #ccc;
}

input[type="number"],
input[type="text"] {
  width: 30%;
  padding: 5px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
input[type="number"]:disabled {
  background-color: #f9f9f9;
}
div {
  margin-top: 20px;
}
span {
  margin: 0 10px;
}
</style>
