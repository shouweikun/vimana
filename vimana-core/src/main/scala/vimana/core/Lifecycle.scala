package vimana.core

/**
  * Created by john_liu on 2019/5/30.
  *
  * 生命周期声明
  *
  * @author Neighborhood.aka.laplace
  *
  */
trait Lifecycle[T] {

  /**
    * 初始化服务
    *
    * @param opts 配置信息
    * @return true is behalf of success
    */
  def init(opts: T): Boolean

  /**
    * 关闭服务
    */
  def shutdown(): Unit


}
