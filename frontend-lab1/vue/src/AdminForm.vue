<template>
<div>
  <div>
    <button type="submit" @click="close" style="margin-left: 80%">Закрыть вкладку</button>
  </div>
  <div>
    <table>
      <thead>
      <th>Пользователь</th>
      <th>Операция</th>
      </thead>
      <tbody>
      <tr v-for="username in usernames" :key="usernames">
        <td>{{username.username}}</td>
        <td><button type="submit" @click="add(username.username)">Добавить</button></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</template>
<script>
export default {
  data() {
    return {
      usernames: new Array(0)
    }
  },
  methods: {
    add(username) {
      fetch(`http://localhost:1298/api/admin/addAdmin`, {
        method: "POST",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        }),
        body: JSON.stringify({
          username: username,
          usernameAdmin: localStorage.getItem("username")
        })
      }).then((response) => {
        return response.text()
      })
    },
    close(){
      this.$router.push({name: 'main'});
    }
  },
  mounted: function (){
    window.setInterval(()=>{
      fetch("http://localhost:1298/api/admin/get", {
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
          this.usernames=request
        }
      )
    },2000)
  }
}
</script>
<style scoped>
/* Стили для общего контейнера */
div {
  margin: 20px;
}

button[type="submit"] {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 10px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  text-align: left;
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
</style>
