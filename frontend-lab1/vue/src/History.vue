<script>
export default {
  data() {
    return {
      id: 1,
      dots:new Array(0)
    }
  },
  methods:{
    close(){
      this.$router.push({name: 'add'});
    },
  },
  mounted: function () {
    window.setInterval(() => {
      fetch("http://localhost:1298/api/LabWork/getFiles", {
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
    }, 3000)
  },
}
</script>

<template>
  <div>
  <div style="margin-left: 80%; margin-top: 5%">
    <button type="submit" @click="close">Закрыть вкладку</button>
  </div>
  <table class="contain">
    <thead class="containhead" id="frame">
    <tr>
      <th style="width: 5%">id</th>
      <th style="width: 5%">status</th>
      <th style="width: 5%">пользователь</th>
      <th style="width: 5%">количество</th>
      <th>Текст файла</th>
    </tr>
    </thead>
    <tr v-for="dot in dots" :key="dot">
      <td style="width: 5%">{{dot.id}}</td>
      <td style="width: 5%">{{dot.status}}</td>
      <td style="width: 5%">{{dot.username}}</td>
      <td style="width: 5%">{{dot.lenght}}</td>
      <td>{{dot.jsonString}}</td>
    </tr>
  </table>
  </div>
</template>

<style scoped>
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
  margin-top: 1%;
  text-align: center;
  vertical-align:middle;
  background: rgba(196, 119, 23, 0.96);
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
</style>
