package id.my.mufidz.antreeorder.base

interface ItemListener<T> : ItemClick {
    fun onItemClick(data: T)
}