package vimana.util

/**
  * Created by john_liu on 2019/5/30.
  *
  * 可拷贝的标识
  *
  * @author neighborhood.aka.laplace
  */
trait Copiable[T] {

  def copy(): T
}
