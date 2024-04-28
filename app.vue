<template>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
</template>
<script setup>
useHead({
  titleTemplate:(title)=>{
    return title ? `${title} | همیشه بهار ` : 'همیشه بهار '
  }
})
let axis=ref([])
onMounted(() => {
  const shapes = document.querySelectorAll('.shape-move')
  shapes.forEach(
      (item,index)=>{
        if(Math.random()>0.5){
          axis.value.push(1)
        }else{
          axis.value.push(-1)
        }
      }
  )
  const base = document.body
  base.addEventListener('mousemove', (e) => {
    shapes.forEach((item, index) => {
      let finalX=(e.clientX * 30) / window.innerWidth
      let finalY = (e.clientY* 30) / window.innerHeight
      item.style.transform=`translate(${axis.value[index]*finalX}px , ${axis.value[index]*finalY}px)`
    })
  })
})
</script>
