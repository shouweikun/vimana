package vimana.conf

import vimana.entity.PeerId
import vimana.util.Copiable

import scala.collection.mutable

/**
  * 节点的配置信息，储存了当前raft协议中的peer成员信息
  *
  * @param iterable initial peers
  */
class Configuration(
                     iterable: Iterable[PeerId] = Iterable.empty
                   ) extends Copiable[Configuration] with Iterable[PeerId] {

  private val peers: scala.collection.mutable.HashSet[PeerId] = new mutable.HashSet[PeerId]
  peers ++ iterable


  override def copy(): Configuration = new Configuration(peers)

  override def iterator: Iterator[PeerId] = peers.iterator

  def getPeerSet = peers.toSet

  def setPeers(iterable: Iterable[PeerId]): Unit = {
    peers.clear()
    peers ++ iterable
  }

  def addPeer(peerId: PeerId): Boolean = peers.add(peerId)

  def removePeer(peerId: PeerId): Boolean = peers.remove(peerId)

  def removePeers(iterable: Iterable[PeerId]) = iterable.foreach(removePeer(_))

  def contains(peerId: PeerId): Boolean = peers.contains(peerId)

}

object Configuration {
  def parse(conf: String): Option[Configuration] = {
    Option(conf).map {
      conf =>
        val re = new Configuration
        conf
          .split(",")
          .foreach(peerStr => PeerId.parse(peerStr).foreach(re.addPeer(_)))
        re
    }
  }

  /**
    * 返回c1 diff c2 ,c2 diff c1
    *
    * @param c1 待处理的configuration1
    * @param c2 待处理的configuration2
    * @return newC1，newC2
    */
  def diff(c1: Configuration, c2: Configuration): (Configuration, Configuration) = {
    val newC1 = c1.copy()
    val newC2 = c2.copy()
    newC1.removePeers(c2.peers)
    newC2.removePeers(c1.peers)
    (newC1, newC2)
  }
}

