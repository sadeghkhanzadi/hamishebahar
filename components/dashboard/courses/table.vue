<script setup lang="ts">
const {data} = defineProps ({
  data:Array
})
const emit = defineEmits(['show' , 'edit' , 'delete' ])
const {$date} = useNuxtApp()
</script>

<template>
<section class="bg-white flex flex-col gap-y-3  p-5 rounded overflow-x-auto">
  <div class="title" >
    <h3 class="font-bold">لیست همه  کلاس ها </h3>
  </div>
  <table class="w-full">
    <thead>
    <tr>
      <th>ردیف</th>
      <th>کد درس</th>
      <th>عنوان</th>
      <th>متن</th>
      <th>قیمت</th>
      <th>تاریخ شروع</th>
      <th>تاریخ پایان </th>
      <th>وضعیت</th>
      <th>نام استاد</th>
      <th>دسته بندی</th>
      <th>گالری</th>
      <th>عملیات</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item , index) in data" :key="index">
      <td class="px-2" >{{index+1}}</td>
      <td class="px-2" >{{item.courseCode}}</td>
      <td class="px-2" >{{item.title}}</td>
      <td class="px-2" >{{item.text}}</td>
      <td class="px-2" >{{item.coste.toLocaleString()}}</td>
      <td class="px-2" >{{$date(item.startTime)}}</td>
      <td class="px-2" >{{$date(item.endTime)}}</td>
      <td class="px-2" ><span :class="` w-fit text-white mx-auto px-3 py-1 rounded ${ item.is_active ? 'bg-blue-500' :'bg-red-500'}`">{{item.is_active === true ? 'فعال' : "غیرفعال"}}</span></td>
      <td class="px-2" >{{item.teachers.firstName}} {{item.teachers.lastName}}</td>
      <td class="px-2" >{{item.category.name}}</td>
      <td class="px-2" >
        <ul >
          <li  v-for="(item,index) in item.medias" :key="index">
            <figure class="w-[40px] h-[40px] p-0.5 rounded border" v-if="item.name && item.pathFile"><img class="w-full h-full text-xs object-cover" :src="item.pathFile" :alt="item.name"></figure>
          </li>
        </ul>
      </td>
      <td class="px-2">
        <button
            @click="navigateTo({path:`/dashboard/courses/edit/${item.courseCode}`})"
            class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-yellow-700 text-white bg-yellow-500 inline-flex items-center justify-center rounded px-2 py-3">
          <i class="fa-solid fa-pen fa-xs"/>
        </button>
        <button
            @click="emit('delete' , item.id , item.title)"
            class=" mx-1 cursor-pointer transition  duration-200 ease-in hover:bg-red-700 text-white bg-red-500 inline-flex items-center justify-center rounded px-2 py-3">
          <i class="fa-solid fa-trash-alt fa-xs"/>
        </button>
      </td>
    </tr>
    </tbody>
  </table>
</section>
</template>

<style scoped lang="scss">
table, th, td {
  border: 1px solid rgba(0, 0, 0, 0.15);
  text-align: center;
  font-size: var(--text-md);
  font-weight: var(--font-medium);
  padding-top: 5px;
  padding-bottom: 5px;
}

th {
  font-size: var(--text-md);
  font-weight: var(--font-bold);
}
</style>
