package vimana.core

import vimana.entity.eventsource.RaftCommand
import vimana.entity.{NodeId, PeerId}
import vimana.option.{NodeOptions, RaftOptions}

/**
  * Created by john_liu on 2019/5/29.
  */
trait Node {

  def leaderId: PeerId

  def nodeId: NodeId

  def groupId: String

  def nodeOptions: NodeOptions

  def raftOptions: RaftOptions

  def isLeader: Boolean

  def applyCommand(command: RaftCommand): Unit

  def readIndex(requestContext: Array[Byte])


}
