

<template>
<div>
  <div>
    <table>
      <thead>
      <th>username</th>
      <th></th>
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

</style>
